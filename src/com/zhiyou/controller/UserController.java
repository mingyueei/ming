package com.zhiyou.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.zhiyou.entity.Complain;
import com.zhiyou.entity.Info;
import com.zhiyou.entity.PageResult;
import com.zhiyou.entity.Role;
import com.zhiyou.entity.RolePrivilege;
import com.zhiyou.entity.User;
import com.zhiyou.entity.UserRole;
import com.zhiyou.entity.UserRoleId;
import com.zhiyou.service.ComplainService;
import com.zhiyou.service.InfoService;
import com.zhiyou.service.RoleService;
import com.zhiyou.service.UserService;


public class UserController extends ActionSupport {
//不要加@Component
    //接收参数


    public static String complainState_DSL = "0";
    public static String complainState_YSL = "1";
    public static HashMap<String, String> complainStateMAP;

    static {
        complainStateMAP = new HashMap<>();
        complainStateMAP.put(complainState_DSL, "待受理");
        complainStateMAP.put(complainState_YSL, "已受理");
    }

    public static String infoType_TZ = "1";
    public static String infoType_ZC = "0";
    public static HashMap<String, String> infoTypeMAP;

    static {
        infoTypeMAP = new HashMap<>();
        infoTypeMAP.put(infoType_TZ, "通知公告");
        infoTypeMAP.put(infoType_ZC, "政策速递");
    }

    private String user_id;
    private String account;
    private User user;
    private User uu;
    private List<Info> infoList;
    private Map map = new HashMap();
    private List<User> users;
    private PageResult<User> pageResult = new PageResult<User>();
    private String page;
    private List<Role> roleList;
    private Role role;
    private String[] userRoleIds;
    //文件上传属性
    private File headImg;
    //struts2中固定的文件名获取方式：上诉属性+FileName，如：img+FileName
    private String headImgFileName;
    //上传位置(自己约定)
    private String path = "/upload";
    private String[] selectedRow;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUu() {
        return uu;
    }

    public void setUu(User uu) {
        this.uu = uu;
    }

    public UserService getUs() {
        return us;
    }


    public void setUs(UserService us) {
        this.us = us;
    }

    public InfoService getIservice() {
        return iservice;
    }

    public void setIservice(InfoService iservice) {
        this.iservice = iservice;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public PageResult<User> getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult<User> pageResult) {
        this.pageResult = pageResult;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String[] getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(String[] selectedRow) {
        this.selectedRow = selectedRow;
    }


    //注入service对象
    @Autowired
    UserService us;
    @Autowired
    InfoService iservice;
    @Autowired
    ComplainService cs;
    @Autowired
    RoleService rs;

    Logger log = Logger.getLogger(this.getClass());

    public String login() {
//		我想使用log4j记录一下日志：使用步骤
//		记录有人准备登录
//		1.得到小log

//		2.小log可以调用不同级别的方法，记录不同级别的信息
        log.info("有用户准备登录了");
        return "login";
    }

    //	登录
    public String selectByAccount() {
        uu = us.selectByAccount(user.getAccount());
        System.out.println("进入selectByAccount");
        System.out.println("输入的：" + user.getAccount());
        if (uu != null) {
            System.out.println("查询的：" + uu.getAccount());
            if (uu.getPassword().equals(user.getPassword())) {
                if (uu.getState() == 1) {
                    log.info("登录啦！！");
                    Map<String, Object> session = ActionContext.getContext().getSession();
                    session.put("SYS_USER", uu);
//			        查询信息发布
                    infoList = iservice.selectInfo();
                    ActionContext.getContext().put("infoList", infoList);
                    ActionContext.getContext().put("infoTypeMap", infoTypeMAP);

//					根据name查询投诉信息	
                    List<Complain> complainList = cs.selectComByName(uu.getName());
                    ActionContext.getContext().put("complainList", complainList);
                    ActionContext.getContext().put("complainStateMap", complainStateMAP);
                    return "selectByAccount";
                } else {
                    this.addFieldError("loginMessage", "用户状态已失效");
                    System.out.println("用户状态已失效");
                    return "error";
                }

            } else {
                this.addFieldError("loginMessage", "密码输入错误");
                System.out.println("密码输入错误");
                return "error";
            }
        } else {
            System.out.println("账号不存在");
            this.addFieldError("loginMessage", "账号不存在");
            return "error";
        }
    }

    //	点击纳税服务
    public String left() {

        return "success";
    }

    public String frame() {

        return "success";
    }

    public String top() {

        return "success";
    }

    //	回到工作主页
    public String goHome() {

//        查询信息发布
        infoList = iservice.selectInfo();
        ActionContext.getContext().put("infoList", infoList);
        ActionContext.getContext().put("infoTypeMap", infoTypeMAP);
        Map<String, Object> session = ActionContext.getContext().getSession();
        uu = (User) session.get("SYS_USER");
//		根据name查询投诉信息	
        List<Complain> complainList = cs.selectComByName(uu.getName());
        ActionContext.getContext().put("complainList", complainList);
        ActionContext.getContext().put("complainStateMap", complainStateMAP);
        return SUCCESS;
    }

    //	查询展示用户信息
    public String listUI() {
        int pageSize = 3;
        pageResult.setPageNo(1);
        if (page != null) {
            pageResult.setPageNo(Integer.parseInt(page));
        }
        int startIndex = (pageResult.getPageNo() - 1) * pageSize;
        users = us.selectUser(startIndex, pageSize, user);
        pageResult.setItems(users);
        pageResult.setTotalCount(us.selectUser(0, 0, user).size());
        if (pageResult.getTotalCount() != 0) {
            pageResult.setTotalPageCount((int) Math.ceil(pageResult.getTotalCount() * 1.0 / pageSize));
        }

        return "success";
    }

    //	去新增页面
    public String addUI() {
        roleList = rs.selectRole(0, 0, role);
        ActionContext.getContext().put("roleList", roleList);

        return "success";
    }

    //	新增
    public String add() {
        System.out.println("添加用户了");
        HashSet<UserRole> userRole = new HashSet<>();
        Role role2 = new Role();
        if (userRoleIds != null) {
            for (int i = 0; i < userRoleIds.length; i++) {
                System.out.println("所属角色：" + userRoleIds[i]);
                role2.setRoleId(userRoleIds[i]);
                userRole.add(new UserRole(new UserRoleId(role2, user)));

            }
            System.out.println("userRole" + userRole);
        }

        user.setUserRoles(userRole);
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        //上传头像
        //1、得到真实路径
        if (headImgFileName != null) {
            String realPath = this.getRealPath(path);
            System.out.println("真实路径：" + realPath);
            //2、得到新的文件名
            System.out.println("旧的文件名：" + headImgFileName);
            String newName = this.getName(headImgFileName);
            System.out.println("新的文件名：" + newName);
            //3、目标文件对象
            File file = new File(realPath + "/" + newName);
            //4、得到需要的参数
            try {
                FileUtils.copyFile(headImg, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setHeadImg("user/" + newName);
        }

        user.setDate(nousedate);
        us.saveUser(user);
        return "success2";
    }

    //	上传头像处理真实路径
    public String getRealPath(String path) {
        //得到真实路径
        String realPath = ServletActionContext.getServletContext().getRealPath(path);
        //如果是第一次上传可能没有这个文件夹，所以要判断下
        File file = new File(realPath);
        if (!file.exists()) {//不存在
            file.mkdirs();//创建文件夹
        }

        return realPath;
    }

    //写辅助方法，处理上传文件名重复的问题
    public String getName(String oldName) {
        //获取后缀
        String substring = oldName.substring(oldName.lastIndexOf("."));
        //使用uuid产生随机的字符串
        String newName = UUID.randomUUID().toString() + substring;
        return newName;
    }

    //	账号校验
    public void verifyAccount() throws Exception {
        uu = us.selectByAccount(user.getAccount());
        String msg = "true";
        if (uu != null) {
            msg = "false";
        }
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(msg.getBytes());
        outputStream.close();

    }

    //	删除
    public String delete() {
        us.deleteUser(user.getId());
        return "success2";
    }

    //	去到编辑页面
    public String editUI() {
        roleList = rs.selectRole(0, 0, role);
        ActionContext.getContext().put("roleList", roleList);
        user = us.selectById(user.getId());
        if (user.getUserRoles() != null) {

            userRoleIds = new String[user.getUserRoles().size()];
            int i = 0;
            Set<UserRole> userRoles = user.getUserRoles();
            for (UserRole userRole : userRoles) {
                userRoleIds[i++] = userRole.getId().getRole().getRoleId();
            }
        }
        return "success";
    }

    //	编辑
    public String edit() {
        System.out.println("要编辑了");
        /*user.getUserRoles().remove(user.getUserRoles());*/

        us.deleteUser(user.getId());
        HashSet<UserRole> userRole = new HashSet<>();
        Role role2 = new Role();
        if (userRoleIds != null) {
            for (int i = 0; i < userRoleIds.length; i++) {
                System.out.println("所属角色：" + userRoleIds[i]);
                role2.setRoleId(userRoleIds[i]);
                userRole.add(new UserRole(new UserRoleId(role2, user)));

            }
            System.out.println("userRole" + userRole);
        }

        user.setUserRoles(userRole);
        Date date = new Date();
        Timestamp nousedate = new Timestamp(date.getTime());
        //上传头像
        //1、得到真实路径
        if (headImgFileName != null) {
            String realPath = this.getRealPath(path);
            System.out.println("真实路径：" + realPath);
            //2、得到新的文件名
            System.out.println("旧的文件名：" + headImgFileName);
            String newName = this.getName(headImgFileName);
            System.out.println("新的文件名：" + newName);
            //3、目标文件对象
            File file = new File(realPath + "/" + newName);
            //4、得到需要的参数
            try {
                FileUtils.copyFile(headImg, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            user.setHeadImg("user/" + newName);
        }
        user.setDate(nousedate);
        us.saveUser(user);

        return "success2";
    }

    //	批量删除
    public String deleteSelected() {
        System.out.println("要批量删除了");
        us.deleteRoleByIds(selectedRow);
        return "success2";
    }

    //	导出用户列表
    public String exportExcel() throws Exception {

        //1、创建工作簿
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        //2、创建工作表
        HSSFSheet createSheet = hssfWorkbook.createSheet("用户列表");

        //3、合并单元格
        CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 8);
        createSheet.addMergedRegion(cellRangeAddress);
        //4、创建行  （默认从0开始）
        HSSFRow createRow = createSheet.createRow(0);
        HSSFRow createRow2 = createSheet.createRow(1);
        //5、创建单元格     （默认从0开始）
        HSSFCell createCell = createRow.createCell(0);

        HSSFCell createCell1 = createRow2.createCell(0);
        HSSFCell createCell2 = createRow2.createCell(1);
        HSSFCell createCell3 = createRow2.createCell(2);
        HSSFCell createCell4 = createRow2.createCell(3);
        HSSFCell createCell5 = createRow2.createCell(4);
        HSSFCell createCell6 = createRow2.createCell(5);
        HSSFCell createCell7 = createRow2.createCell(6);
        HSSFCell createCell8 = createRow2.createCell(7);
        HSSFCell createCell9 = createRow2.createCell(8);

        //6、设置单元格的值
        createCell.setCellValue("用户列表");
        createCell1.setCellValue("用户名");
        createCell2.setCellValue("部门");
        createCell3.setCellValue("性别");
        createCell4.setCellValue("邮箱");
        createCell5.setCellValue("手机号");
        createCell6.setCellValue("出生日期");
        createCell7.setCellValue("状态");
        createCell8.setCellValue("备注");
        createCell9.setCellValue("修改日期");

        users = us.selectUser(0, 0, user);
        for (int i = 0; i < users.size(); i++) {
            HSSFRow row = createSheet.createRow(i + 2);
            row.createCell(0).setCellValue(users.get(i).getName());
            row.createCell(1).setCellValue(users.get(i).getDept());
            if (users.get(i).getGender() == 0) {
                row.createCell(2).setCellValue("男");
            } else {
                row.createCell(2).setCellValue("女");
            }
            row.createCell(3).setCellValue(users.get(i).getEmail());
            row.createCell(4).setCellValue(users.get(i).getMobile());
            if (users.get(i).getBirthday() != null) {
                Timestamp birthday = users.get(i).getBirthday();

                row.createCell(5).setCellValue(birthday.toString());
            }

            if (users.get(i).getState() == 0) {
                row.createCell(6).setCellValue("无效");
            } else {
                row.createCell(6).setCellValue("有效");
            }
            row.createCell(7).setCellValue(users.get(i).getMemo());
            if (users.get(i).getDate() != null) {
                Timestamp updateTime = users.get(i).getDate();
                System.out.println("修改时间1：" + updateTime.toString());

                row.createCell(8).setCellValue(updateTime.toString());
            }


        }

        //7、利用输出流封装文件abc.xls
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\Develop\\workspace\\National_tax\\WebContent\\WEB-INF\\jsp\\nsfw\\user\\userExcel.xls");
        //8、生产工作簿
        hssfWorkbook.write(fileOutputStream);
        //9、关闭资源
        fileOutputStream.close();
		
		/*//输出excel
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/msexcel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+new String("用户列表.xls".getBytes(),"ISO-8859-1"));
		ServletOutputStream outputStream=null;*/
        return "success2";
    }

    //	导入用户列表
    public String importExcel() throws Exception {
        //和刚才的过程相反
//		1、将abc.xls文件读成输入流
        FileInputStream fileInputStream = new FileInputStream("D:\\Develop\\workspace\\National_tax\\WebContent\\WEB-INF\\jsp\\nsfw\\user\\userExcel.xls");
//		2、将流变成工作簿
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(fileInputStream);
//		3、先得到表
        HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
//		4、得到行
        if (sheet.getPhysicalNumberOfRows() > 2) {
            List<User> userList = new ArrayList<User>();
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); i++) {

                User user2 = new User();
                //4.读取单元格
                Row row = sheet.getRow(i);

                //用户名
                Cell cell1 = row.getCell(0);
                user2.setName(cell1.getStringCellValue());

                //所属部门
                Cell cell2 = row.getCell(1);
                user2.setDept(cell2.getStringCellValue());
                //性别
                Cell cell3 = row.getCell(2);
                if (cell3.getStringCellValue().equals("男")) {
                    user2.setGender(0);
                } else {
                    user2.setGender(1);
                }

                //邮箱
                Cell cell4 = row.getCell(3);

                user2.setEmail(cell4.getStringCellValue());
                //手机号
                String mobile = "";
                Cell cell5 = row.getCell(4);
                try {
                    mobile = cell5.getStringCellValue();
                } catch (Exception e) {
                    double dMobile = cell5.getNumericCellValue();
                    mobile = BigDecimal.valueOf(dMobile).toString();
                }
                user2.setMobile(mobile);

                //生日
                Cell cell6 = row.getCell(5);
                if (cell6 != null) {
                    System.out.println("生日1：" + cell6.getStringCellValue());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date parse = simpleDateFormat.parse(cell6.getStringCellValue());
                    System.out.println("生日2：" + parse);
                    Timestamp birthday = new Timestamp(parse.getTime());
                    System.out.println("生日3：" + birthday);
                    user2.setBirthday(birthday);
                }

                //状态
                Cell cell7 = row.getCell(6);
                if (cell7.getStringCellValue().equals("有效")) {
                    user2.setState(1);
                } else {
                    user2.setState(0);
                }


                //备注
                Cell cell8 = row.getCell(7);
                user2.setMemo(cell8.getStringCellValue());
                //修改日期
                Cell cell9 = row.getCell(8);
                if (cell9 != null) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date parse = simpleDateFormat.parse(cell9.getStringCellValue());

                    Timestamp timestamp = new Timestamp(parse.getTime());
                    user2.setDate(timestamp);
                }


                userList.add(user2);
                ActionContext.getContext().put("userList", userList);
            }

        }

//		8、关闭资源
        fileInputStream.close();
        return "success";
    }

    //	退出
    public String logout() {

        return SUCCESS;
    }

}
