package com.xiaofan.car.util.generator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName: EntityUtil
 * @Description: 自动生成MyBatis的实体类、实体映射XML文件、Mapper
 * @author EX-XIONGTAO600
 * @date 2016年4月20日 下午8:04:18
 */
public class EntityUtil {

    private static final Logger logger = LoggerFactory
            .getLogger(EntityUtil.class);

    private final String type_char = "char";

    private final String type_date = "date";

    private final String type_timestamp = "timestamp";

    private final String type_int = "int";

    private final String type_bigint = "bigint";

    private final String type_text = "text";

    private final String type_bit = "bit";

    private final String type_decimal = "decimal";

    private final String type_blob = "blob";

    private final String moduleName = "caroa_db";
    // 对应模块名称（根据自己模块做相应调整!!!务必修改^_^）
    // private final String moduleName = "paycenter_db"; //
    // 对应模块名称（根据自己模块做相应调整!!!务必修改^_^）
    private final String bean_path = "d:/entity_bean";

    private final String mapper_path = "d:/entity_mapper";

    private final String xml_path = "d:/entity_mapper/xml";

    private final String bean_package = "com.xiaofan.car.persistence.model";

    private final String mapper_package = "com.xiaofan.car.dao.repository";

    private final String driverName = "com.mysql.jdbc.Driver";

    private final String root = "root";// user

    private final String pass = "qwer1234";// password

    private final String url = "jdbc:mysql://47.93.20.244:3306/" + moduleName
            + "?characterEncoding=utf8";

    private String tableName = null;

    private String beanName = null;

    private String mapperName = null;

    private Connection conn = null;

    private PreparedStatement pstate = null;

    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);
        conn = DriverManager.getConnection(url, root, pass);
    }

    /**
     * @Title: getTables @Description: 获取所有的表 @return @throws
     *         SQLException @throws
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();
        pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString(1);
            // if ( tableName.toLowerCase().startsWith("yy_") ) {
            tables.add(tableName);
            // }
        }
        return tables;
    }

    private void processTable(String table) {
        StringBuffer sb = new StringBuffer(table.length());
        String tableNew = table.toLowerCase();
        String[] tables = tableNew.split("_");
        String temp = null;
        if (tables.length > 1) {
            for (int i = 1; i < tables.length; i++) {
                temp = tables[i].trim();
                sb.append(temp.substring(0, 1).toUpperCase())
                        .append(temp.substring(1));
            }
        } else {
            for (int i = 0; i < tables.length; i++) {
                temp = tables[i].trim();
                sb.append(temp.substring(0, 1).toUpperCase())
                        .append(temp.substring(1));
            }
        }
        beanName = sb.toString();
        mapperName = beanName + "Mapper";
    }

    private String processType(String type) {
        if (type.indexOf(type_char) > -1) {
            return "String";
        } else if (type.indexOf(type_bigint) > -1) {
            return "Long";
        } else if (type.indexOf(type_int) > -1) {
            return "Integer";
        } else if (type.indexOf(type_date) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(type_text) > -1) {
            return "String";
        } else if (type.indexOf(type_timestamp) > -1) {
            return "java.util.Date";
        } else if (type.indexOf(type_bit) > -1) {
            return "Boolean";
        } else if (type.indexOf(type_decimal) > -1) {
            return "java.math.BigDecimal";
        } else if (type.indexOf(type_blob) > -1) {
            return "byte[]";
        }
        return null;
    }

    private String processField(String field) {
        StringBuffer sb = new StringBuffer(field.length());
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0].substring(0, 1))
                .append(fields[0].substring(1, 2).toLowerCase())
                .append(fields[0].substring(2));
        for (int i = 1; i < fields.length; i++) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase())
                    .append(temp.substring(1));
        }

        return sb.toString();
    }

    /**
     * 将实体类名首字母改为小写
     *
     * @param beanName
     * @return
     */
    private String processResultMapId(String beanName) {
        return beanName;
    }

    /**
     * 构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text)
            throws IOException {
        bw.newLine();
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" **/");
        return bw;
    }

    /**
     * 构建方法上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildMethodComment(BufferedWriter bw, String text)
            throws IOException {
        bw.newLine();
        bw.write("\t/**");
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t * " + text);
        bw.newLine();
        bw.write("\t * ");
        bw.newLine();
        bw.write("\t **/");
        return bw;
    }

    /**
     * 生成实体类
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildEntityBean(List<String> columns, List<String> types,
                                 List<String> comments, String tableComment, String table)
            throws IOException {
        File folder = new File(bean_path);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File beanFile = new File(bean_path, beanName + ".java");
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + bean_package + ";");
        bw.newLine();
        bw.write("import lombok.Data;");
        bw.newLine();
        // bw.write("import javax.persistence.*;");
        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        // bw.write("@Entity");
        // bw.newLine();
        bw.write("@Data");
        bw.newLine();
        // bw.write("@Table(name = " + "\"" + table + "\"" + ")");
        // bw.newLine();
        bw.write("public class " + beanName + "{");
        bw.newLine();
        bw.newLine();
        int size = columns.size();
        bw.write("\t/**" + comments.get(0) + "**/");
        bw.newLine();
        // bw.write("\t@Column(name = " + "\"" + columns.get(0) + "\"" + ")");
        // bw.newLine();
        // bw.write("\t@Id");
        // bw.newLine();
        bw.write("\tprivate " + processType(types.get(0)) + " "
                + processField(columns.get(0)) + ";");
        bw.newLine();
        bw.newLine();
        for (int i = 1; i < size; i++) {
            bw.write("\t/**" + comments.get(i) + "**/");
            bw.newLine();
            // bw.write("\t@Column(name = " + "\"" + columns.get(i) + "\"" +
            // ")");
            // bw.newLine();
            bw.write("\tprivate " + processType(types.get(i)) + " "
                    + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        // 生成get 和 set方法 String tempField = null;
        String _tempField = null;
        String tempType = null;
        String tempField = null;
        for (int i = 0; i < size; i++) {
            tempType = processType(types.get(i));
            _tempField = processField(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase()
                    + _tempField.substring(1);
            bw.newLine();
            bw.write("\tpublic void set" + tempField + "(" + tempType + " "
                    + _tempField + "){");
            bw.newLine();
            bw.write("\t\tthis." + _tempField + "=" + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
            bw.newLine();
            bw.write("\tpublic " + tempType + " get" + tempField + "(){");
            bw.newLine();
            bw.write("\t\treturn this." + _tempField + ";");
            bw.newLine();
            bw.write("\t}");
            bw.newLine();
        }
        bw.newLine();
        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     * 构建Mapper文件
     *
     * @throws IOException
     */
    private void buildMapper() throws IOException {
        File folder = new File(mapper_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperFile = new File(mapper_path, mapperName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(mapperFile), "utf-8"));
        bw.write("package " + mapper_package + ";");
        bw.newLine();
        bw.newLine();
        bw.write("import " + bean_package + "." + beanName + ";");
        bw.newLine();
        // bw.write("import tk.mybatis.mapper.common.Mapper;");
        bw = buildClassComment(bw, mapperName + "数据库操作接口类");
        bw.newLine();
        bw.newLine();
        // bw.write("public interface " + mapperName + " extends " +
        // mapper_extends+ "<" + beanName + "> {");
        bw.write("public interface " + mapperName + "{");
        bw.newLine();
        bw.newLine();

        bw.write("}");
        bw.flush();
        bw.close();
    }

    /**
     * 构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildMapperXml(List<String> columns, List<String> types,
                                List<String> comments) throws IOException {
        File folder = new File(xml_path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File mapperXmlFile = new File(xml_path, mapperName + ".xml");
        BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write(
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.newLine();
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + mapper_package + "." + mapperName
                + "\">");
        bw.newLine();
        bw.newLine();

        bw.write("\t<!--实体映射-->");
        bw.newLine();
        bw.write("\t<resultMap id=\"BaseResultMap\" type=\"" + bean_package
                + "." + beanName + "\">");
        bw.newLine();
        bw.write("\t\t<!--" + comments.get(0) + "-->");
        bw.newLine();
        bw.write("\t\t<id property=\"" + this.processField(columns.get(0))
                + "\" column=\"" + columns.get(0) + "\" />");
        bw.newLine();
        int size = columns.size();
        for (int i = 1; i < size; i++) {
            bw.write("\t\t<!--" + comments.get(i) + "-->");
            bw.newLine();
            bw.write("\t\t<result property=\""
                    + this.processField(columns.get(i)) + "\" column=\""
                    + columns.get(i) + "\" />");
            bw.newLine();
        }
        bw.write("\t</resultMap>");

        bw.newLine();
        bw.newLine();
        bw.newLine();

        // 下面开始写SqlMapper中的方法 // this.outputSqlMapperMethod(bw, columns, types);
        buildSQL(bw, columns, types);
        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }

    private void buildSQL(BufferedWriter bw, List<String> columns,
                          List<String> types) throws IOException {
        int size = columns.size();
        // 通用结果列
        bw.write("\t<!-- 通用查询结果列-->");
        bw.newLine();
        bw.write("\t<sql id=\"Base_Column_List\">");
        bw.newLine();

        bw.write("");
        for (int i = 0; i < size; i++) {
            bw.write("\t" + columns.get(i));
            if (i != size - 1) {
                bw.write(",");
            }
        }

        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();

        // 查询（根据主键ID查询）
        bw.write("\t<!-- 查询（根据主键ID查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"selectByPrimaryKey\" resultType=\""
                + bean_package + "." + beanName
                + "\" parameterType=\"java.lang." + processType(types.get(0))
                + "\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{"
                + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完

        // 删除（根据主键ID删除）
        bw.write("\t<!--删除：根据主键ID删除-->");
        bw.newLine();
        bw.write(
                "\t<delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang."
                        + processType(types.get(0)) + "\">");
        bw.newLine();
        bw.write("\t\t DELETE FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{"
                + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
        bw.newLine();
        // 删除完

        // 添加insert方法
        bw.write("\t<!-- 添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"insert\" parameterType=\"" + bean_package + "."
                + beanName + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write(" \t\t(");
        for (int i = 0; i < size; i++) {
            bw.write(columns.get(i));
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t\t VALUES ");
        bw.newLine();
        bw.write(" \t\t(");
        for (int i = 0; i < size; i++) {
            bw.write("#{" + processField(columns.get(i)) + "}");
            if (i != size - 1) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // 添加insert完

        // --------------- insert方法（匹配有值的字段）
        bw.write("\t<!-- 添加 （匹配有值的字段）-->");
        bw.newLine();
        bw.write("\t<insert id=\"insertSelective\" parameterType=\""
                + bean_package + "." + beanName + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write(
                "\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        String tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + ",");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write("\t\t </trim>");
        bw.newLine();

        bw.write(
                "\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for (int i = 0; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + "!=null\">");
            bw.newLine();
            bw.write("\t\t\t\t #{" + tempField + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        // --------------- 完毕

        // 修改update方法
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKeySelective\" parameterType=\""
                + bean_package + "." + beanName + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write(" \t\t <set> ");
        bw.newLine();

        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">");
            bw.newLine();
            bw.write("\t\t\t\t " + columns.get(i) + " = #{" + tempField + "},");
            bw.newLine();
            bw.write("\t\t\t</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write(" \t\t </set>");
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(0) + " = #{"
                + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
        // update方法完毕
        // ----- 修改（匹配有值的字段）
        bw.write("\t<!-- 修 改-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKey\" parameterType=\""
                + bean_package + "." + beanName + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write("\t\t SET ");

        bw.newLine();
        tempField = null;
        for (int i = 1; i < size; i++) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t " + columns.get(i) + " = #{" + tempField + "}");
            if (i != size - 1) {
                bw.write(",");
            }
            bw.newLine();
        }

        bw.write("\t\t WHERE " + columns.get(0) + " = #{"
                + processField(columns.get(0)) + "}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
    }

    /**
     * 获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    private Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while (results.next()) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }

    public void generate(List<String> tablename)
            throws ClassNotFoundException, SQLException, IOException {
        init();
        String prefix = "show full fields from ";
        List<String> columns = null;
        List<String> types = null;
        List<String> comments = null;
        List<String> tables = getTables();
        if (tables.containsAll(tablename)) {
            tables.clear();
            tables.addAll(tablename);
        }
        Map<String, String> tableComments = getTableComment();
        for (String table : tables) {
            columns = new ArrayList<String>();
            types = new ArrayList<String>();
            comments = new ArrayList<String>();
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while (results.next()) {
                columns.add(results.getString("FIELD"));
                types.add(results.getString("TYPE"));
                comments.add(results.getString("COMMENT"));
            }
            tableName = table;
            processTable(table);
            // this.outputBaseBean();
            String tableComment = tableComments.get(tableName);
            buildEntityBean(columns, types, comments, tableComment, table);
            buildMapper();
            buildMapperXml(columns, types, comments);
        }
        pstate.close();
        conn.close();
    }

    public static void main(String[] args) throws RuntimeException {
        try {
            List<String> tables = new ArrayList<String>();

//            tables.add("t_ledger_info");
//            tables.add("t_device");
//            tables.add("t_employee");
//            tables.add("t_role");
//            tables.add("t_vip");
//            tables.add("t_vip_level");
//            tables.add("t_device_info");
//            tables.add("t_check_info");
//            tables.add("t_tpm_bill");
//            tables.add("t_tmp_bill_detail");
//            tables.add("t_tmp_problem_log");
//            tables.add("t_device_info");
//            tables.add("t_service_info");
//            tables.add("t_memu");
//            tables.add("t_attachment_info");
            tables.add("t_arrange_info");
            new EntityUtil().generate(tables);
            // 自动打开生成文件的目录
            Runtime.getRuntime().exec("cmd /c start explorer D:\\");
        } catch (ClassNotFoundException e) {
            logger.error("EntityUtil异常", e);
        } catch (SQLException e) {
            logger.error("EntityUtil异常", e);
        } catch (IOException e) {
            logger.error("EntityUtil异常", e);
        }
    }
}
