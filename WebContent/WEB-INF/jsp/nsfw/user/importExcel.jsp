<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户列表</title>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    <%
        pageContext.setAttribute("basePath", request.getContextPath() + "/");
    %>
</head>
<body class="rightBody">
<s:debug></s:debug>
<div class="p_d_1">
    <div class="p_d_1_1">
        <div class="content_info">
            <div class="c_crumbs">
                <div><b></b><strong>用户列表</strong></div>
            </div>


            <div class="t_list" style="margin:0px; border:0px none;">
                <table width="100%" border="0">
                    <tr class="t_tit">

                        <td width="140" align="center">用户名</td>

                        <td width="160" align="center">所属部门</td>
                        <td width="80" align="center">性别</td>
                        <td align="center">电子邮箱</td>
                        <td width="140" align="center">手机号</td>
                        <td width="220" align="center">出生日期</td>
                        <td width="80" align="center">状态</td>
                        <td width="80" align="center">备注</td>
                        <td width="220" align="center">修改日期</td>
                    </tr>
                    <s:iterator value="userList">


                        <td align="center"><s:property value="name"/></td>

                        <td align="center"><s:property value="dept"/></td>
                        <td align="center"><s:property value="gender==0?'男':'女'"/></td>
                        <td align="center"><s:property value="email"/></td>
                        <td align="center"><s:property value="mobile"/></td>
                        <td align="center"><s:property value="birthday"/></td>
                        <td align="center"><s:property value="state==1?'有效':'无效'"/></td>
                        <td align="center"><s:property value="memo"/></td>
                        <td align="center"><s:property value="date"/></td>
                        </tr>
                    </s:iterator>
                </table>
                <a class="yh" href="${ctx }nsfw/user_listUI.action" target="mainFrame">返回</a>
            </div>
        </div>

    </div>
</div>
</body>
</html>