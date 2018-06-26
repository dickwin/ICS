/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : ics

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-06-11 18:46:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_1
-- ----------------------------
DROP TABLE IF EXISTS `t_1`;
CREATE TABLE `t_1` (
  `id` int(11) NOT NULL,
  `tet1` varchar(255) DEFAULT NULL,
  `tsdgs` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_1
-- ----------------------------
INSERT INTO `t_1` VALUES ('1', '3', '3');
INSERT INTO `t_1` VALUES ('2', '2', '2');
INSERT INTO `t_1` VALUES ('5', '3', null);

-- ----------------------------
-- Table structure for t_code
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code` (
  `id` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `code` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_code
-- ----------------------------
INSERT INTO `t_code` VALUES ('1', 'query', 'package com.ics.controller;\r\n\r\nimport org.springframework.web.bind.annotation.PathVariable;\r\nimport org.springframework.web.bind.annotation.RequestMapping;\r\nimport org.springframework.web.bind.annotation.RequestMethod;\r\nimport org.springframework.web.bind.annotation.RestController;\r\n\r\nimport com.wordnik.swagger.annotations.ApiOperation;\r\n\r\n@RestController\r\n@RequestMapping(value=\"/users\")\r\npublic class test {\r\n    /**\r\n     *\r\n     * @return\r\n     */\r\n    @ApiOperation(value=\"Get all users\",notes=\"requires noting\")\r\n    @RequestMapping(method=RequestMethod.GET)\r\n    public Object getUsers(){\r\n    	return null;\r\n    }\r\n\r\n    @ApiOperation(value=\"Get user with id\",notes=\"requires the id of user\")\r\n    @RequestMapping(value=\"/{name}\",method=RequestMethod.GET)\r\n    public String getUserById(@PathVariable String name){\r\n        return \"hello world\";\r\n    }\r\n}\r\n');

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NUM` int(11) DEFAULT NULL,
  `PID` int(11) DEFAULT NULL,
  `sn` varchar(45) DEFAULT NULL,
  `fu` varchar(255) DEFAULT NULL,
  `tp` varchar(255) DEFAULT NULL,
  `vs` int(11) DEFAULT NULL,
  `code` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('2', '1', '1', 'tc', '江苏同步信息技术有限公司', null, '2', 'package com.ics.controller;\r\n\r\nimport org.springframework.web.bind.annotation.PathVariable;\r\nimport org.springframework.web.bind.annotation.RequestMapping;\r\nimport org.springframework.web.bind.annotation.RequestMethod;\r\nimport org.springframework.web.bind.annotation.ResponseBody;\r\nimport org.springframework.web.bind.annotation.RestController;\r\n\r\nimport com.wordnik.swagger.annotations.ApiOperation;\r\n\r\n@RestController\r\n@RequestMapping(value=\"/users\")\r\npublic class test extends BaseController{\r\n    /**\r\n     *\r\n     * @return\r\n     */\r\n    @ApiOperation(value=\"Get all users\",notes=\"requires noting\")\r\n    @RequestMapping(value=\"ets\",method=RequestMethod.GET)\r\n    @ResponseBody\r\n    public Object getUsers(){\r\n    	return \"sbc\";\r\n    }\r\n\r\n    @ApiOperation(value=\"Get user with id\",notes=\"requires the id of user\")\r\n    @RequestMapping(value=\"name\",method=RequestMethod.GET)\r\n    @ResponseBody\r\n    public String getUserById(@PathVariable String name){\r\n        return \"hello world\";\r\n    }\r\n}\r\n');

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function` (
  `id` int(11) NOT NULL,
  `mid` int(11) NOT NULL,
  `funname` varchar(200) NOT NULL,
  `funmethod` varchar(50) NOT NULL,
  `funincode` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_function
-- ----------------------------

-- ----------------------------
-- Table structure for t_model
-- ----------------------------
DROP TABLE IF EXISTS `t_model`;
CREATE TABLE `t_model` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `tablename` varchar(100) DEFAULT NULL,
  `status` tinyint(4) unsigned zerofill DEFAULT '0000' COMMENT '执行状态0未执行 1已经执行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_model
-- ----------------------------
INSERT INTO `t_model` VALUES ('1', 'abc', 't_1', '0000');
INSERT INTO `t_model` VALUES ('4', 'test', 't_test', '0000');

-- ----------------------------
-- Table structure for t_template
-- ----------------------------
DROP TABLE IF EXISTS `t_template`;
CREATE TABLE `t_template` (
  `id` int(11) NOT NULL,
  `type` varchar(20) DEFAULT NULL COMMENT '0接口 1serivce 2 xml',
  `template` text,
  `name` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_template
-- ----------------------------
INSERT INTO `t_template` VALUES ('1', '1', 'package com.ics.mapper;\r\n\r\nimport java.util.List;\r\nimport java.util.Map;\r\n\r\npublic interface @ModelMapper {\r\n	public int @addModel(Map paramMap);\r\n	public int @delModel(Map paramMap);\r\n	public int @updateModel(Map paramMap);\r\n	public List @getModel(Map paramMap);\r\n}', '接口模板');
INSERT INTO `t_template` VALUES ('2', '2', 'package com.ics.service;\r\n\r\nimport java.util.List;\r\nimport java.util.Map;\r\n\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.stereotype.Service;\r\n\r\nimport com.ics.mapper.@ModelMapper;\r\n@Service\r\npublic class @ModelService {\r\n    @Autowired\r\n    private @ModelMapper @model;\r\n    \r\n    public int @addModel(Map paramMap){\r\n        int ls=@model.@addModel(paramMap);\r\n        return ls;\r\n    }\r\n    public int @delModel(Map paramMap){\r\n    	int ls= @model.@delModel(paramMap);\r\n        return ls;\r\n    }\r\n    public int @updateModel(Map paramMap){\r\n    	int ls=@model.@updateModel(paramMap);\r\n        return ls;\r\n    }\r\n    public List @getModel(Map paramMap){\r\n        List ls=@model.@getModel(paramMap);\r\n        return ls;\r\n    }\r\n}', 'service类模板');
INSERT INTO `t_template` VALUES ('3', '3', '<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n<mapper namespace=\"com.ics.mapper.@ModelMapper\">\r\n	<!-- 增加Model -->\r\n	<insert id=\"@addModel\" parameterType=\"java.util.HashMap\">\r\n		insert into\r\n		@tablename\r\n		(@insertsqlcolumn)\r\n		values\r\n		(@insertsqlvalue)\r\n	</insert>\r\n	<!-- 删除Model -->\r\n	<delete id=\"@delModel\" parameterType=\"java.util.HashMap\">\r\n		delete from\r\n		@tablename\r\n		where\r\n		<if test=\"id !=null and id !=\'\'\">\r\n		   id = #{id} \r\n		</if>\r\n	</delete>\r\n	<!-- 更新Model -->\r\n	<update id=\"@updateModel\" parameterType=\"java.util.HashMap\">\r\n		update\r\n		@tablename set @updatesql where id=#{id}\r\n	</update>\r\n	<!-- 查询Model -->\r\n	<select id=\"@getModel\" resultType=\"java.util.HashMap\">\r\n		select * from @tablename\r\n	</select>\r\n</mapper>', 'xml配置文件');
INSERT INTO `t_template` VALUES ('4', '4', 'package com.ics.controller;\r\n\r\nimport java.util.HashMap;\r\nimport java.util.List;\r\nimport java.util.Map;\r\n\r\nimport org.apache.log4j.LogManager;\r\nimport org.apache.log4j.Logger;\r\nimport org.springframework.beans.factory.annotation.Autowired;\r\nimport org.springframework.stereotype.Controller;\r\nimport org.springframework.web.bind.annotation.RequestMapping;\r\nimport org.springframework.web.bind.annotation.RequestMethod;\r\nimport org.springframework.web.bind.annotation.ResponseBody;\r\n\r\nimport com.ics.service.@ModelService;\r\nimport com.ics.vo.Result;\r\n\r\n@Controller\r\n@RequestMapping(\"/@model\")\r\npublic class @ModelController extends BaseController{\r\n	private static Logger log = LogManager.getLogger(@ModelController.class.getName());\r\n\r\n	@Autowired\r\n	private @ModelService @model;\r\n    \r\n	@RequestMapping(value = \"/@getModel\", method = RequestMethod.GET)\r\n	@ResponseBody\r\n    public Object @getModel() {\r\n		Result result = new Result(1, null);\r\n		try {\r\n			List rs = @model.@getModel(paramMap);\r\n			Map data = new HashMap();\r\n			data.put(\"list\", rs);\r\n			result.setData(data);\r\n			result.setRet(0);\r\n		} catch (Exception e) {\r\n			log.error(e.getLocalizedMessage(), e);\r\n		}\r\n\r\n        return result;\r\n    }\r\n	\r\n	@RequestMapping(value = \"/@addModel\", method = RequestMethod.GET)\r\n	@ResponseBody\r\n    public Object @addModel() {\r\n		Result result = new Result(1, null);\r\n		try {\r\n			int rs = @model.@addModel(paramMap);\r\n			Map data = new HashMap();\r\n			data.put(\"list\", rs);\r\n			result.setData(data);\r\n			result.setRet(0);\r\n		} catch (Exception e) {\r\n			log.error(e.getLocalizedMessage(), e);\r\n		}\r\n        return result;\r\n    }\r\n	\r\n	@RequestMapping(value = \"/@delModel\", method = RequestMethod.GET)\r\n	@ResponseBody\r\n    public Object @delModel() {\r\n		Result result = new Result(1, null);\r\n		try {\r\n			int rs = @model.@delModel(paramMap);\r\n			Map data = new HashMap();\r\n			data.put(\"list\", rs);\r\n			result.setData(data);\r\n			result.setRet(0);\r\n		} catch (Exception e) {\r\n			log.error(e.getLocalizedMessage(), e);\r\n		}\r\n        return result;\r\n    }\r\n	\r\n	@RequestMapping(value = \"/@updateModel\", method = RequestMethod.GET)\r\n	@ResponseBody\r\n    public Object updateModel() {\r\n		Result result = new Result(1, null);\r\n		try {\r\n			int rs = @model.@updateModel(paramMap);\r\n			Map data = new HashMap();\r\n			data.put(\"list\", rs);\r\n			result.setData(data);\r\n			result.setRet(0);\r\n		} catch (Exception e) {\r\n			log.error(e.getLocalizedMessage(), e);\r\n		}\r\n        return result;\r\n    }\r\n}', 'controller模板');

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_test
-- ----------------------------
INSERT INTO `t_test` VALUES ('1', 'ff', '2');
INSERT INTO `t_test` VALUES ('3', '4', '4');
INSERT INTO `t_test` VALUES ('31', '4', '4');
