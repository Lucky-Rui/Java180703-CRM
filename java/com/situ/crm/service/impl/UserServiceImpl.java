package com.situ.crm.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.crm.common.ServerResponse;
import com.situ.crm.entity.Permission;
import com.situ.crm.entity.Role;
import com.situ.crm.entity.User;
import com.situ.crm.entity.UserRole;
import com.situ.crm.mapper.PermissionMapper;
import com.situ.crm.mapper.RoleMapper;
import com.situ.crm.mapper.UserMapper;
import com.situ.crm.mapper.UserRoleMapper;
import com.situ.crm.service.IUserService;
import com.situ.crm.util.UserContext;
import com.situ.crm.vo.LayUISelectMVO;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public ServerResponse pageList(Integer page, Integer limit, User user) {
		// 1、使用PageHelper插件设置分页
		PageHelper.startPage(page, limit);
		// 2、执行查询
		List<User> list = userMapper.pageList(user);
		// 3、使用PageInfo对结果进行包装
		PageInfo pageInfo = new PageInfo(list);
		Integer count = (int) pageInfo.getTotal();// 得到总数量

		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		try {
			int count = userMapper.deleteByPrimaryKey(id);
			if (count == 1) {
				return ServerResponse.createSuccess("删除成功");
			} else {
				return ServerResponse.createError("删除失败");
			}
		} catch (Exception e) {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public ServerResponse deleteAll(String ids) {
		String[] idArray = ids.split(",");
		int count = userMapper.deleteAll(idArray);
		if (count == idArray.length) {
			return ServerResponse.createSuccess("删除成功");
		} else {
			return ServerResponse.createError("删除失败");
		}
	}

	@Override
	public User selectByPrimaryKey(Integer userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public ServerResponse add(User user, String roles) {
		try {
			// 将角色插入到数据库中
			int count = userMapper.insert(user);
			// 将角色-权限多对多关系放到角色-权限表中
			String[] roleIds = roles.split(",");
			for (String roleId : roleIds) {
				UserRole userRole = new UserRole(user.getId(), Integer.parseInt(roleId));
				userRoleMapper.insert(userRole);
			}
			return ServerResponse.createSuccess();
		} catch (Exception e) {
			return ServerResponse.createError("添加失败");
		}
	}

	@Override
	public ServerResponse selectUserAndRoles(Integer userId) {
		Map<String, Object> map = new HashMap<>();
		// 查询user信息放到map中
		User user = userMapper.selectByPrimaryKey(userId);
		map.put("user", user);
		// 查询所有的权限放到map中
		List<Role> roles = roleMapper.pageList(new Role());
		List<LayUISelectMVO> list = new ArrayList<>();
		for (Role role : roles) {
			LayUISelectMVO layUISelectMVO = new LayUISelectMVO();
			layUISelectMVO.setId(role.getId());
			layUISelectMVO.setName(role.getSn());
			layUISelectMVO.setStatus(1);
			list.add(layUISelectMVO);
		}
		map.put("allRoles", list);
		// 这个角色User对应的所有角色id的数组放到map中
		List<Integer> userSelectedRoles = userRoleMapper.selectRolesByUserId(userId);
		map.put("selectIds", userSelectedRoles.toArray());

		return ServerResponse.createSuccess("成功", map);
	}

	@Override
	public ServerResponse update(User user, String roles) {
		try {
			// 将角色插入到数据库中
			int count = userMapper.updateByPrimaryKey(user);
			// 删除这个角色下面原来的权限
			userRoleMapper.deleteByUserId(user.getId());
			// 将角色-权限多对多关系放到角色-权限表中
			String[] roleIds = roles.split(",");
			for (String roleId : roleIds) {
				UserRole userRole = new UserRole(user.getId(), Integer.parseInt(roleId));
				userRoleMapper.insert(userRole);
			}
			return ServerResponse.createSuccess("更新成功");
		} catch (Exception e) {
			return ServerResponse.createError("更新失败");
		}
	}

	@Override
	public ServerResponse login(String name, String password, HttpSession session) {
		// 设置请求到当前的线程中
		// UserContext.setLocalThread(request);
		UserContext.session = session;
		User user = userMapper.login(name, password);
		if (user != null) {
			// 把当前用户放到session中
			session.setAttribute(UserContext.USER_IN_SESSION, user);
			// 查询该用户拥有的权限,并放到session中
			List<Permission> permissions = permissionMapper.selectByUserId(user.getId());
			session.setAttribute(UserContext.PERMISSION_IN_SESSION, permissions);
			return ServerResponse.createSuccess("登录成功");
		}
		return ServerResponse.createError("登录失败");
	}

	@Override
	public List<User> selectXiaoShouUser() {
		return userMapper.selectXiaoShouUser();
	}

	@Override
	public void exportExcel(HttpServletResponse response) {
		// 1.查询用户列表
		List<User> list = userMapper.pageList(new User());
		for (User user : list) {
			System.out.println(user);
		}
		// 2.写到excel
		// 1、创建工作簿：Workbook。
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 2、创建工作表：Sheet。
		HSSFSheet sheet = workbook.createSheet("用户列表");
		// 设置默认的列宽
		sheet.setDefaultColumnWidth(25);
		// 2.1创建合并单元格对象
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2, 6, 1, 12);
		sheet.addMergedRegion(cellRangeAddress);
		// 3、创建行：Row。
		HSSFRow rowTitle = sheet.createRow(2);
		// 4、创建单元格：Cell。
		HSSFCell cellTitle = rowTitle.createCell(1);

		HSSFCellStyle style = createStyle(workbook, 25);
		cellTitle.setCellStyle(style);
		cellTitle.setCellValue("用户列表");

		HSSFRow rowHead = sheet.createRow(7);
		/* 创建单元格 */
		String[] array = { "编号", "名称", "邮箱" };
		for (int i = 0; i < array.length; i++) {
			HSSFCell cellHead = rowHead.createCell(i + 1);
			HSSFCellStyle headStyle = createStyle(workbook, 13);
			cellHead.setCellStyle(headStyle);
			cellHead.setCellValue(array[i]);
		}

		HSSFCellStyle styleCenter = workbook.createCellStyle();
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);// center
																// horizontal
																// alignment
		styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中

		// 遍历输出数据库中的数据到excel中
		for (int i = 0; i < list.size(); i++) {
			HSSFRow row = sheet.createRow(8 + i);
			HSSFCell cellId = row.createCell(1);
			cellId.setCellStyle(styleCenter);
			cellId.setCellValue(list.get(i).getId());
			HSSFCell cellName = row.createCell(2);
			cellName.setCellStyle(styleCenter);
			cellName.setCellValue(list.get(i).getName());
			HSSFCell cellEmail = row.createCell(3);
			cellEmail.setCellStyle(styleCenter);
			cellEmail.setCellValue(list.get(i).getEmail());
		}

		response.setContentType("applicaiton/x-excel");
		// 3.以附件的形式返回给浏览器
		ServletOutputStream outputStream = null;
		try {
			response.setHeader("Content-Disposition",
					"attachment;filename=" + new String("用户列表.xls".getBytes(), "ISO-8859-1"));
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 输出excel到文件
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private HSSFCellStyle createStyle(HSSFWorkbook workbook, int fontSize) {
		// 创建单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// center horizontal
														// alignment
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗
		font.setFontHeightInPoints((short) fontSize);// 设置字体大小
		// 加载字体到样式
		style.setFont(font);
		return style;
	}
}
