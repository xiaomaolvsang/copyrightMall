package com.copyright.mall.util;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

/**
 *自定义生成 *.java、*.xml、*Mapper.java *Service.java I*Service
 *
 *@author lijian
 *@version 2.0.0
 */
public class Cg {

    /**
     **********************************使用前必读*******************
     **
     ** 使用需要修改的值：{
     *										1-dbName：数据库名；
     *										2-user：数据库名；
     *										3-password：数据库密码；
     *										4-url:数据库连接；
     *	 }
     **
     ***********************************************************
     */

    private final String type_char = "char";

    private final String type_varchar = "varchar";

    private final String type_date = "date";

    private final String type_datetime = "datetime";

    private final String type_timestamp = "timestamp";

    private final String type_int = "int";

    private final String type_bigint = "bigint";

    private final String type_text = "text";

    private final String type_bit = "bit";

    private final String type_decimal = "decimal";

    private final String type_blob = "blob";

    private final String type_float = "float";

    private final String type_double = "double";

    private final String type_enum = "enum";

    /**
     *
     *
     *
     *
     * 修改的地方
     */
    private final String dbName = "go_db"; // 数据库名

    private final String moduleName = ""; // 模块名

    private final String bean_path = "/Users/dxm/Desktop/cg/entity_bean/"+dbName;

    private final String mapper_path = "/Users/dxm/Desktop/cg/entity_mapper_dao/"+dbName;

    private final String service_path = "/Users/dxm/Desktop/cg/entity_service/"+dbName;

    private final String i_service_path = "/Users/dxm/Desktop/cg/entity_service/"+dbName;

    private final String xml_path = "/Users/dxm/Desktop/cg/entity_mapper/"+dbName;

    private final String bean_package = "com.copyright.mall.bean";

    private final String mapper_package = "com.copyright.mall.dao";

    private final String service_package = "com.copyright.mall.service";

    private final String i_service_package = "com.copyright.mall.service.impl";

    private final String driverName = "com.mysql.jdbc.Driver";

    private final String user = "root";

    private final String password = "lin5241!";

    private final String url = "jdbc:mysql://cdb-1tr98sga.bj.tencentcdb.com:10130/" + dbName + "?characterEncoding=utf8";

    private String tableName = null;

    private String beanName = null;

    private String mapperName = null;

    private Connection conn = null;


    /**
     * 加载驱动
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void init() throws ClassNotFoundException, SQLException {
        Class.forName(driverName);//"com.mysql.jdbc.Driver"
        conn = DriverManager.getConnection(url, user, password);
    }

    /**
     *  获取所有的表 or 配置要生成的表
     * @return
     * @throws SQLException
     */
    private List<String> getTables() throws SQLException {
        List<String> tables = new ArrayList<String>();
        PreparedStatement pstate = conn.prepareStatement("show tables");
        ResultSet results = pstate.executeQuery();
       /* while ( results.next() ) {
            String tableName = results.getString(1);
            //          if ( tableName.toLowerCase().startsWith("gms_") ) {
            tables.add(tableName);
            //          }
        }*/
        //新的表写在这里
        tables.add("mall");
        return tables;
    }

    /**
     * 将表名转换为bean名称
     * @param table
     */
    private void processTable(String table) {
        StringBuffer sb = new StringBuffer(table.length());
//        String tableNew = table.toLowerCase();
        String[] tables = table.split("_");
        String temp = null;
        for ( int i = 0 ; i < tables.length ; i++ ) {
            temp = tables[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1).toLowerCase());
        }
        beanName = sb.toString();
        mapperName = mapper_package+"."+beanName + "Mapper";
    }

    /**
     * 将数据库字段类型转换为xml中的jdbc类型（有引号，在resultMap中使用）
     * @param type
     * @return
     */
    private String processXmlType(String type) {
        if ( type.indexOf(type_varchar) > -1 ) {
            return "jdbcType=\"VARCHAR\"";
        } if ( type.indexOf(type_char) > -1 ) {
            return "jdbcType=\"CHAR\"";
        } else if ( type.indexOf(type_bigint) > -1 ) {
            return "jdbcType=\"BIGINT\"";
        } else if ( type.indexOf(type_int) > -1 ) {
            return "jdbcType=\"INTEGER\"";
        }else if ( type.indexOf(type_datetime) > -1 ) {
            return "jdbcType=\"TIMESTAMP\"";
        }  else if ( type.indexOf(type_date) > -1 ) {
            return "jdbcType=\"DATE\"";
        }else if ( type.indexOf(type_text) > -1 ) {
            return "jdbcType=\"LONGVARCHAR\"";
        } else if ( type.indexOf(type_timestamp) > -1 ) {
            return "jdbcType=\"TIMESTAMP\"";
        } else if ( type.indexOf(type_bit) > -1 ) {
            return "jdbcType=\"BIT\"";
        } else if ( type.indexOf(type_decimal) > -1 ) {
            return "jdbcType=\"DECIMAL\"";
        } else if ( type.indexOf(type_blob) > -1 ) {
            return "jdbcType=\"BLOB\"";
        }else if ( type.indexOf(type_float) > -1 ) {
            return "jdbcType=\"FLOAT\"";
        }else if ( type.indexOf(type_double) > -1 ) {
            return "jdbcType=\"DOUBLE\"";
        }else if ( type.indexOf(type_enum) > -1 ) {
            return "jdbcType=\"VARCHAR\"";
        }
        return null;
    }

    /**
     * 将数据库字段类型转换为xml中的jdbc类型(去掉引号，在sql语句中使用)
     * @param type
     * @return
     */
    private String processXmlType2( String type ) {
        if ( type.indexOf(type_varchar) > -1 ) {
            return "jdbcType=VARCHAR";
        } if ( type.indexOf(type_char) > -1 ) {
            return "jdbcType=CHAR";
        } else if ( type.indexOf(type_bigint) > -1 ) {
            return "jdbcType=BIGINT";
        } else if ( type.indexOf(type_int) > -1 ) {
            return "jdbcType=INTEGER";
        } else if ( type.indexOf(type_datetime) > -1 ) {
            return "jdbcType=TIMESTAMP";
        } else if ( type.indexOf(type_date) > -1 ) {
            return "jdbcType=DATE";
        }else if ( type.indexOf(type_text) > -1 ) {
            return "jdbcType=LONGVARCHAR";
        } else if ( type.indexOf(type_timestamp) > -1 ) {
            return "jdbcType=TIMESTAMP";
        } else if ( type.indexOf(type_bit) > -1 ) {
            return "jdbcType=BIT";
        } else if ( type.indexOf(type_decimal) > -1 ) {
            return "jdbcType=DECIMAL";
        } else if ( type.indexOf(type_blob) > -1 ) {
            return "jdbcType=BLOB";
        }else if ( type.indexOf(type_float) > -1 ) {
            return "jdbcType=FLOAT";
        }else if ( type.indexOf(type_double) > -1 ) {
            return "jdbcType=DOUBLE";
        }else if ( type.indexOf(type_enum) > -1 ) {
            return "jdbcType=VARCHAR";
        }
        return null;
    }

    /**
     * 将数据库字段类型转换为java类型
     * @param type
     * @return
     */
    private String processType(String type) {
        if ( type.indexOf(type_char) > -1 ) {
            return "String";
        } else if ( type.indexOf(type_bigint) > -1 ) {
            return "Long";
        } else if ( type.indexOf(type_int) > -1 ) {
            return "Integer";
        } else if ( type.indexOf(type_date) > -1 ) {
            return "Date";
        } else if ( type.indexOf(type_text) > -1 ) {
            return "String";
        } else if ( type.indexOf(type_timestamp) > -1 ) {
            return "Date";
        } else if ( type.indexOf(type_bit) > -1 ) {
            return "Boolean";
        } else if ( type.indexOf(type_decimal) > -1 ) {
            return "java.math.BigDecimal";
        } else if ( type.indexOf(type_blob) > -1 ) {
            return "byte[]";
        }else if ( type.indexOf(type_float) > -1 ) {
            return "Float";
        }else if ( type.indexOf(type_double) > -1 ) {
            return "Double";
        }else if ( type.indexOf(type_enum) > -1 ) {
            return "String";
        }
        return null;
    }

    /**
     * 将数据库字段名转化为bean字段名
     * @param field
     * @return
     */
    private String processField( String field ) {
        StringBuffer sb = new StringBuffer(field.length());
        //field = field.toLowerCase();
        String[] fields = field.split("_");
        String temp = null;
        sb.append(fields[0]);
        for ( int i = 1 ; i < fields.length ; i++ ) {
            temp = fields[i].trim();
            sb.append(temp.substring(0, 1).toUpperCase()).append(temp.substring(1));
        }
        return sb.toString();
    }


    /**
     * 实体用全名
     *
     * @param beanName
     * @return
     */
    private String processResultMapId(String beanName) {
//        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);//用别名
        return bean_package+"."+beanName;//用全名
    }

    /**
     *  将实体类名首字母改为小写
     * @param beanName
     * @return
     */
    private String processResultMapId2( String beanName ) {
        return beanName.substring(0, 1).toLowerCase() + beanName.substring(1);//用别名
    }

    /**
     *  构建类上面的注释
     *
     * @param bw
     * @param text
     * @return
     * @throws IOException
     */
    private BufferedWriter buildClassComment(BufferedWriter bw, String text) throws IOException {
        bw.newLine();
        bw.newLine();
        bw.write("/**");
        bw.newLine();
        bw.write(" * ");
        bw.newLine();
        bw.write(" * " + text);
        bw.newLine();
        bw.write(" * @author lijian");
        bw.newLine();
        bw.write(" * @date " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        bw.newLine();
        bw.write(" **/");
        return bw;
    }

    /**
     *  生成实体对象
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildEntityBean(List<String> columns, List<String> types, List<String> comments, String tableComment) throws IOException {
        File folder = new File(bean_path);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File beanFile = new File(bean_path, beanName + ".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + bean_package + ";");
        bw.newLine();
        bw.write("import java.io.Serializable;");
        bw.newLine();
        bw.write("import java.util.Date;");
        bw.newLine();
        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw.newLine();

        bw = buildClassComment(bw, tableComment);
        bw.newLine();

        bw.write("public class " + beanName + " implements Serializable {");
        bw.newLine();
        bw.newLine();

        bw.write("\tprivate static final long serialVersionUID = "+getRandomNum(19)+"L;");
        bw.newLine();
        bw.newLine();

        int size = columns.size();
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write("\t/**" + comments.get(i) + "**/");
            bw.newLine();
            bw.write("\tprivate " + processType(types.get(i)) + " " + processField(columns.get(i)) + ";");
            bw.newLine();
            bw.newLine();
        }
        bw.newLine();
        // 生成get 和 set方法
        String tempField = null;
        String _tempField = null;
        String tempType = null;
        for ( int i = 0 ; i < size ; i++ ) {
            tempType = processType(types.get(i));
            _tempField = processField(columns.get(i));
            tempField = _tempField.substring(0, 1).toUpperCase() + _tempField.substring(1);
            bw.newLine();
            //          bw.write("\tpublic void set" + tempField + "(" + tempType + " _" + _tempField + "){");
            bw.write("\tpublic void set" + tempField + "(" + tempType + " " + _tempField + "){");
            bw.newLine();
            //          bw.write("\t\tthis." + _tempField + "=_" + _tempField + ";");
            bw.write("\t\tthis." + _tempField + " = " + _tempField + ";");
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

        //重写toString
        bw.write("\t@Override");
        bw.newLine();
        bw.write("\tpublic String toString() {");
        bw.newLine();
        String _field = null;
        bw.write("\t\treturn \""+beanName+" [ ");
        for ( int i = 0 ; i < size ; i++ ) {
            _field = processField(columns.get(i));
            bw.write(_field+"= \"+"+_field);
            if((i+1)<size){
                bw.write("+");
                bw.newLine();
                bw.write("\t\t\t\",");
            }
        }
        bw.write("+\"]\";");
        bw.newLine();
        bw.write("\t}");
        bw.newLine();


        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }


    /**
     *  构建实体类映射XML文件
     *
     * @param columns
     * @param types
     * @param comments
     * @throws IOException
     */
    private void buildMapperXml(List<String> columns, List<String> types, List<String> comments ,String tableComment) throws IOException {
        File folder = new File(xml_path);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }
        int idIndex = findId(columns);
        File mapperXmlFile = new File(xml_path, beanName + "Mapper.xml");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(mapperXmlFile)));
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        bw.newLine();
        bw.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" ");
        bw.write("    \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
        bw.newLine();
        bw.write("<mapper namespace=\"" + mapperName + "\">");
        bw.newLine();
        bw.newLine();

        //通用结果集BaseResultMap的生成
        bw.write("\t<!--cg generate by lijian at "+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())+"-->");
        bw.newLine();
        bw.newLine();

        bw.write("\t<!--通用"+tableComment+"表映射-->");
        bw.newLine();
        bw.write("\t<resultMap id=\"BaseResultMap\" type=\""+processResultMapId(beanName)+"\" >");
        bw.newLine();
/*        bw.write("\t\t<id property=\"" + this.processField(columns.get(0)) + "\" column=\"" + columns.get(0) +"\" "
        			+this.processXmlType(types.get(0))+ " />");
        bw.newLine();*/
        int size = columns.size();
        bw.write("\t\t<id property=\"" + this.processField(columns.get(idIndex)) + "\" column=\"" + columns.get(idIndex).toUpperCase() +"\" "
                +this.processXmlType(types.get(idIndex))+ " />");
        bw.newLine();
        for ( int i = 0 ; i < size ; i++ ) {
            if(i == idIndex){
                continue;
            }
            bw.write("\t\t<result property=\"" + this.processField(columns.get(i)) + "\" column=\"" + columns.get(i).toUpperCase() + "\" "
                    + this.processXmlType(types.get(i)) + " />");
            bw.newLine();
        }
        bw.write("\t</resultMap>");

        bw.newLine();
        bw.newLine();
        bw.newLine();


        // 下面开始写SqlMapper中的方法
        buildSQL(bw, columns, types);

        bw.write("</mapper>");
        bw.flush();
        bw.close();
    }


    /**
     *  生成mapper类
     *
     * @param types
     * @throws IOException
     */
    private void buildMapper(List<String> types,String tableComment)throws IOException {
        File folder = new File(mapper_path);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }

        File beanFile = new File(mapper_path, beanName + "Mapper.java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + mapper_package + ";");
        bw.newLine();
        bw.newLine();

        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.Date;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw.newLine();
        bw.newLine();

        bw.write("import "+bean_package+"."+beanName+";");
        bw.newLine();
        bw.newLine();

        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.write("public interface " + beanName + "Mapper {");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic " + beanName + " selectByPrimaryKey("+processType(types.get(0))+" id);");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic int deleteByPrimaryKey("+processType(types.get(0))+" id);");
        bw.newLine();
        bw.newLine();

//        bw.write("\tpublic int deleteByPrimaryKey("+beanName+" "+processResultMapId2(beanName)+");");
//        bw.newLine();
//        bw.newLine();

        bw.write("\tpublic int insertSelective("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic int updateByPrimaryKeySelective("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();
//
//        bw.write("\tpublic int updateByPrimaryKey("+beanName+" "+processResultMapId2(beanName)+");");
//        bw.newLine();
//        bw.newLine();

        bw.write("\tpublic Long selectObjectListPageTotal("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic List<" + beanName + "> selectObjectListPage("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic List<" + beanName + "> selectByObjectList("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     *  生成service接口
     *
     * @param types
     * @throws IOException
     */
    private void buildInterService(List<String> types,String tableComment)throws IOException {
        File folder = new File(i_service_path);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }
        String className = "I"+beanName + "Service";

        File beanFile = new File(i_service_path,className+".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + i_service_package + ";");
        bw.newLine();
        bw.newLine();

        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.Date;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw.newLine();
        bw.newLine();

        bw.write("import "+bean_package+"."+beanName+";");
        bw.newLine();
        bw.newLine();

        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.write("public interface " + className + " {");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic " + beanName + " selectByPrimaryKey("+processType(types.get(0))+" id);");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic int deleteByPrimaryKey("+processType(types.get(0))+" id);");
        bw.newLine();
        bw.newLine();

//        bw.write("\tpublic int deleteByPrimaryKey("+beanName+" "+processResultMapId2(beanName)+");");
//        bw.newLine();
//        bw.newLine();

        bw.write("\tpublic int insertSelective("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic int updateByPrimaryKeySelective("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

//        bw.write("\tpublic int updateByPrimaryKey("+beanName+" "+processResultMapId2(beanName)+");");
//        bw.newLine();
//        bw.newLine();

        bw.write("\tpublic Long selectObjectListPageTotal("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic List<" + beanName + "> selectObjectListPage("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("\tpublic List<" + beanName + "> selectByObjectList("+beanName+" "+processResultMapId2(beanName)+");");
        bw.newLine();
        bw.newLine();

        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }

    /**
     *  生成service 实现类
     *
     * @param types
     * @throws IOException
     */
    private void buildImplService(List<String> types,String tableComment)throws IOException {
        File folder = new File(service_path);
        if ( !folder.exists() ) {
            folder.mkdirs();
        }
        String className = beanName + "Service";

        String i_className = "I" + beanName + "Service";

        File beanFile = new File(service_path,className+".java");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(beanFile)));
        bw.write("package " + service_package + ";");
        bw.newLine();
        bw.newLine();

        bw.write("import java.util.List;");
        bw.newLine();
        bw.write("import java.util.Date;");
        bw.newLine();
        bw.write("import java.util.Map;");
        bw.newLine();
        bw.write("import org.slf4j.Logger;");
        bw.newLine();
        bw.write("import org.slf4j.LoggerFactory;");
        bw.newLine();
        bw.write("import org.springframework.stereotype.Service;");
        bw.newLine();
        bw.write("import javax.annotation.Resource;");
        bw.newLine();
        bw.write("import " + mapper_package + "." + beanName + "Dao;");
        bw.newLine();
        bw.write("import " + i_service_package + "." + i_className+";");
        bw.newLine();
        bw.newLine();

        bw.write("import "+bean_package+"."+beanName+";");
        bw.newLine();

        bw = buildClassComment(bw, tableComment);
        bw.newLine();
        bw.write("@Service");
        bw.newLine();
        bw.write("public class " + className + " implements " + i_className + " {");
        bw.newLine();
        bw.newLine();
        bw.write("\tprivate static Logger logger = LoggerFactory.getLogger("+className+".class);");
        bw.newLine();
        bw.newLine();
        bw.write("\t@Resource");bw.newLine();
        String daoName = processResultMapId2(beanName)+"Dao";
        bw.write("\tprivate "+beanName+"Dao "+daoName+";");bw.newLine();
        bw.newLine();
        bw.write("\t@Override");bw.newLine();
        bw.write("\tpublic " + beanName + " selectByPrimaryKey("+processType(types.get(0))+" id) {");
        bw.newLine();

        bw.write("\t\treturn "+daoName+".selectByPrimaryKey(id);");bw.newLine();

        bw.write("\t}");
        bw.newLine();
        bw.write("\t@Override");bw.newLine();
        bw.write("\tpublic int deleteByPrimaryKey(" + processType(types.get(0)) + " id) {");
        bw.newLine();
        bw.newLine();
        bw.write("\t\treturn "+daoName+".deleteByPrimaryKey(id);");bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw.newLine();
        bw.write("\t@Override");bw.newLine();
        bw.write("\tpublic int insertSelective(" + beanName + " " + processResultMapId2(beanName) + ") {");
        bw.newLine();
        bw.write("\t\treturn "+daoName+".insertSelective("+processResultMapId2(beanName)+");");bw.newLine();


        bw.write("\t}");
        bw.newLine();
        bw.newLine();
        bw.write("\t@Override");bw.newLine();
        bw.write("\tpublic int updateByPrimaryKeySelective(" + beanName + " " + processResultMapId2(beanName) + ") {");
        bw.newLine();
        bw.write("\t\treturn "+daoName+".updateByPrimaryKeySelective("+processResultMapId2(beanName)+");");bw.newLine();

        bw.write("\t}");
        bw.newLine();
        bw.newLine();

//        bw.write("\t@Override");bw.newLine();
//        bw.write("\tpublic int updateByPrimaryKey(" + beanName + " " + processResultMapId2(beanName) + ") {");
//        bw.newLine();
//        bw.write("\t\treturn "+daoName+".updateByPrimaryKey("+processResultMapId2(beanName)+");");bw.newLine();
//        bw.write("\t}");
//        bw.newLine();
//        bw.newLine();

        bw.write("\t@Override");bw.newLine();
        bw.write("\tpublic Long selectObjectListPageTotal(" + beanName + " " + processResultMapId2(beanName) + ") {");
        bw.newLine();
        bw.write("\t\treturn "+daoName+".selectObjectListPageTotal("+processResultMapId2(beanName)+");");bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw.newLine();
        bw.write("\t@Override");bw.newLine();
        bw.write("\tpublic List<" + beanName + "> selectObjectListPage(" + beanName + " " + processResultMapId2(beanName) + ") {");
        bw.newLine();
        bw.write("\t\treturn "+daoName+".selectObjectListPage("+processResultMapId2(beanName)+");");bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw.newLine();
        bw.write("\t@Override");bw.newLine();
        bw.write("\tpublic List<" + beanName + "> selectByObjectList("+beanName+" "+processResultMapId2(beanName)+"){");
        bw.newLine();
        bw.write("\t\treturn "+daoName+".selectByObjectList("+processResultMapId2(beanName)+");");bw.newLine();
        bw.write("\t}");
        bw.newLine();
        bw.newLine();

        bw.write("}");
        bw.newLine();
        bw.flush();
        bw.close();
    }



    private void buildSQL( BufferedWriter bw, List<String> columns, List<String> types ) throws IOException {
        String tempField = null;

        int size = columns.size();
        int idIndex = findId(columns);
        // 通用结果列
        bw.write("\t<!-- 通用查询结果集合-->");
        bw.newLine();
        bw.write("\t<sql id=\"Base_Column_List\">");
        bw.newLine();

        bw.write("\t"+columns.get(0)+",");
        for ( int i = 1 ; i < size ; i++ ) {
            bw.write("\t" + columns.get(i).toUpperCase());
            if ( i != size - 1 ) {
                bw.write(",");
            }
        }

        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();

        //通用查询条件拼接
        bw.write("\t<!-- 公共查询条件-->");
        bw.newLine();
        bw.write("\t<!-- collection foreach DATE_FORMAT(create_time,'%Y-%m-%d') like CONCAT('%',#{goodsNo,jdbcType=VARCHAR},'%') -->");
        bw.newLine();
        bw.write("\t<!-- <![CDATA[<=]]> date_format(FROM_UNIXTIME(expire_time),'%Y-%c-%d %h:%i:%s') as showExpireTime-->");
        bw.newLine();
        bw.newLine();
        bw.write("\t<sql id=\"conditions\">");
        bw.newLine();

        for (int i = 0 ; i < size ; i++ ) {
            tempField = processField(columns.get(i));
            //todo 数字类型=0的要特殊处理 delFlag != '' 若是0   false
            String type = this.processXmlType2(types.get(i));
            if ("jdbcType=INTEGER".equals(type)){
                bw.write("\t\t<if test=\"" + tempField + " != null \"> and "+ columns.get(i).toUpperCase() + " = #{" + tempField + ","+type+"} </if>");
            }else {
                bw.write("\t\t<if test=\"" + tempField + " != null and "+tempField+" != '' \"> and "+ columns.get(i).toUpperCase() + " = #{" + tempField + ","+type+"} </if>");
            }
            bw.newLine();
        }

        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();

        //通用排序语句
       /* bw.write("\t<!-- 通用排序语句拼接-->");
        bw.newLine();
        bw.write("\t<sql id=\"orderBy\">");
        bw.newLine();
//        bw.write("\t\t<if test=\"sort != null\"> order by #{sort,jdbcType=VARCHAR}");
        bw.write("\t\t<if test=\"sort != null\"> order by ${sort}");
        bw.newLine();
        bw.write("\t\t\t<choose>");
        bw.newLine();
        bw.write("\t\t\t\t<when test=\"flag==0\"> DESC </when>");
        bw.newLine();
        bw.write("\t\t\t\t<otherwise> ASC </otherwise>");
        bw.newLine();
        bw.write("\t\t\t</choose>");
        bw.newLine();
        bw.write("\t\t</if>");
        bw.newLine();
        bw.write("\t</sql>");
        bw.newLine();
        bw.newLine();*/


        // 查询（根据主键ID查询）
        bw.write("\t<!-- 查询（根据主键ID查询） -->");
        bw.newLine();
        bw.write("\t<select id=\"selectByPrimaryKey\" resultMap=\"BaseResultMap"
                + "\" parameterType=\"java.lang." + processType(types.get(idIndex)) + "\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(idIndex).toUpperCase() + " = #{" + processField(columns.get(idIndex)) + ","+this.processXmlType2(types.get(idIndex))+"}");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();
        // 查询完


        // 删除（根据主键ID删除）
        bw.write("\t<!--删除：根据主键ID删除-->");
        bw.newLine();
        bw.write("\t<delete id=\"deleteByPrimaryKey\" parameterType=\"java.lang." + processType(types.get(idIndex)) + "\">");
        bw.newLine();
        bw.write("\t\t DELETE FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(idIndex).toUpperCase() + " = #{" + processField(columns.get(idIndex)) + ","+this.processXmlType2(types.get(idIndex))+"}");
        bw.newLine();
        bw.write("\t</delete>");
        bw.newLine();
        bw.newLine();
        // 删除完

//
//        //按条件删除
//        bw.write("\t<!-- 删除：根据输入条件删除 -->");
//        bw.newLine();
//        bw.write("\t<delete id=\"deleteByObject\" parameterType=\"" + processResultMapId(beanName) + "\">");
//        bw.newLine();
//        bw.write("\t\t DELETE FROM " + tableName);
//        bw.newLine();
//        bw.write("\t\t WHERE 1=1");
//        bw.newLine();
//        bw.write("\t\t <include refid=\"conditions\" />");
//        bw.newLine();
//        bw.write("\t</delete>");
//        bw.newLine();
//        bw.newLine();


        // 添加insert方法
        /*bw.write("\t<!-- 添加 -->");
        bw.newLine();
        bw.write("\t<insert id=\"insert\" parameterType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write(" \t\t(");
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write(columns.get(i));
            if ( i != size - 1 ) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t\t VALUES ");
        bw.newLine();
        bw.write(" \t\t(");
        for ( int i = 0 ; i < size ; i++ ) {
            bw.write("#{" + processField(columns.get(i)) + "}");
            if ( i != size - 1 ) {
                bw.write(",");
            }
        }
        bw.write(") ");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();*/
        // 添加insert完


        //---------------  insert方法（匹配有值的字段）
        bw.write("\t<!-- 添加 （匹配有值的字段,不建议使用）-->");
        bw.newLine();
        bw.write("\t<insert id=\"insertSelective\" parameterType=\"" + processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t INSERT INTO " + tableName);
        bw.newLine();
        bw.write("\t\t <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for ( int i = 0 ; i < size ; i++ ) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\"> "+ columns.get(i).toUpperCase() + ",</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();

        bw.write("\t\t <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
        bw.newLine();

        tempField = null;
        for ( int i = 0 ; i < size ; i++ ) {
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + "!=null\">#{"+ tempField + ","+this.processXmlType2(types.get(i))+"},</if>");
            bw.newLine();
        }

        bw.write("\t\t </trim>");
        bw.newLine();
        bw.write("\t</insert>");
        bw.newLine();
        bw.newLine();
        //---------------  完毕


        // 修改update方法（匹配有值的字段）
        bw.write("\t<!-- 根据主键修改输入的值-->");
        bw.newLine();
        bw.write("\t<update id=\"updateByPrimaryKeySelective\" parameterType=\"" +processResultMapId(beanName) + "\">");
        bw.newLine();
        bw.write("\t\t UPDATE " + tableName);
        bw.newLine();
        bw.write(" \t\t <set> ");
        bw.newLine();

        tempField = null;
        for ( int i = 0 ; i < size ; i++ ) {
            if(i == idIndex){
                continue;
            }
            tempField = processField(columns.get(i));
            bw.write("\t\t\t<if test=\"" + tempField + " != null\">"+ columns.get(i).toUpperCase() + " = #{" + tempField + ","+this.processXmlType2(types.get(i))+"},</if>");
            bw.newLine();
        }

        bw.newLine();
        bw.write(" \t\t </set>");
        bw.newLine();
        bw.write("\t\t WHERE " + columns.get(idIndex).toUpperCase() + " = #{" + processField(columns.get(idIndex)) + ","+this.processXmlType2(types.get(idIndex))+"}");
        bw.newLine();
        bw.write("\t</update>");
        bw.newLine();
        bw.newLine();
        // update方法完毕

        // ----- 修改（全量修改）
//        bw.write("\t<!-- 根据主键全量修改,不建议使用-->");
//        bw.newLine();
//        bw.write("\t<update id=\"updateByPrimaryKey\" parameterType=\"" + processResultMapId(beanName) + "\">");
//        bw.newLine();
//        bw.write("\t\t UPDATE " + tableName);
//        bw.newLine();
//        bw.write("\t\t SET ");
//
//        bw.newLine();
//        tempField = null;
//        for ( int i = 1 ; i < size ; i++ ) {
//            tempField = processField(columns.get(i));
//            bw.write("\t\t\t " + columns.get(i) + " = #{" + tempField + "}");
//            if ( i != size - 1 ) {
//                bw.write(",");
//            }
//            bw.newLine();
//        }
//
//        bw.write("\t\t WHERE " + columns.get(0) + " = #{" + processField(columns.get(0)) + "}");
//        bw.newLine();
//        bw.write("\t</update>");
//        bw.newLine();
//        bw.newLine();

        //分页查询
        bw.write("\t<!-- 分页查询 -->");
        bw.newLine();
        bw.write("\t<select id=\"selectObjectListPage\" resultMap=\"BaseResultMap"
                + "\" parameterType=\"java.util.HashMap\" useCache=\"false\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE 1=1");
        bw.newLine();
        bw.write("\t\t <include refid=\"conditions\" />");
        bw.newLine();
        bw.write("\t\t ORDER BY id DESC");
        bw.newLine();
        bw.write("\t\t limit #{startOfPage},#{pageSize}");
//        bw.write("\t\t <include refid=\"orderBy\" />");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();

        //分页查询总数
        bw.write("\t<!-- 分页查询总数 -->");
        bw.newLine();
        bw.write("\t<select id=\"selectObjectListPageTotal\" resultType=\"java.lang.Long"
                + "\" parameterType=\"java.util.HashMap\" useCache=\"false\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t count(*) ");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE 1=1");
        bw.newLine();
        bw.write("\t\t <include refid=\"conditions\" />");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();

        //按条件查询列表
        bw.write("\t<!-- 按条件查询列表 -->");
        bw.newLine();
        bw.write("\t<select id=\"selectByObjectList\" resultMap=\"BaseResultMap"
                + "\" parameterType=\"java.util.HashMap\">");
        bw.newLine();
        bw.write("\t\t SELECT");
        bw.newLine();
        bw.write("\t\t <include refid=\"Base_Column_List\" />");
        bw.newLine();
        bw.write("\t\t FROM " + tableName);
        bw.newLine();
        bw.write("\t\t WHERE 1=1");
        bw.newLine();
        bw.write("\t\t <include refid=\"conditions\" />");
        bw.newLine();
        bw.write("\t</select>");
        bw.newLine();
        bw.newLine();

    }

    private int findId(List<String> columns){
       for (int i =0;i<columns.size();i++){
           if("id".equals(columns.get(i))){
               return i;
           }
       }
       return 0;
    }


    /**
     *  获取所有的数据库表注释
     *
     * @return
     * @throws SQLException
     */
    private Map<String, String> getTableComment() throws SQLException {
        Map<String, String> maps = new HashMap<String, String>();
        PreparedStatement pstate = conn.prepareStatement("show table status");
        ResultSet results = pstate.executeQuery();
        while ( results.next() ) {
            String tableName = results.getString("NAME");
            String comment = results.getString("COMMENT");
            maps.put(tableName, comment);
        }
        return maps;
    }

    private String getRandomNum(int length){
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        code.append(String.valueOf(random.nextInt(90)+1));
        for (int i = 2; i < length; i++) {
            code.append(String.valueOf(random.nextInt(10)));
        }
        return code.toString();
    }

    public void generate() throws ClassNotFoundException, SQLException, IOException {
        init();
        String prefix = "show full fields from ";
        List<String> columns = null;
        List<String> types = null;
        List<String> comments = null;
        PreparedStatement pstate = null;
        List<String> tables = getTables();
        Map<String, String> tableComments = getTableComment();
        for ( String table : tables ) {
            columns = new ArrayList<String>();
            types = new ArrayList<String>();
            comments = new ArrayList<String>();
            pstate = conn.prepareStatement(prefix + table);
            ResultSet results = pstate.executeQuery();
            while ( results.next() ) {
                columns.add(results.getString("FIELD").toLowerCase());
                types.add(results.getString("TYPE"));
                comments.add(results.getString("COMMENT"));
            }
            tableName = table;
            processTable(table);
            //          this.outputBaseBean();
            String tableComment = tableComments.get(tableName);//表名注释
            buildEntityBean(columns, types, comments, tableComment);
            buildMapperXml(columns, types, comments,tableComment);
            buildMapper(types,tableComment);
            buildInterService(types, tableComment);
            buildImplService(types, tableComment);
        }
        conn.close();
    }


    public static void main( String[] args ) {
        try {
            new Cg().generate();
            System.out.println("===============success======================");
            // 自动打开生成文件的目录
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        } catch (SQLException e ) {
            e.printStackTrace();
        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
}
