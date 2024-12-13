/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : xs

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 13/12/2024 14:46:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area`  (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `AreaName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `AreaNameChinese` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 245 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of area
-- ----------------------------
INSERT INTO `area` VALUES (1, 'China', '中国');
INSERT INTO `area` VALUES (2, 'Afghanistan', '阿富汗');
INSERT INTO `area` VALUES (3, 'Albania', '阿尔巴尼亚');
INSERT INTO `area` VALUES (4, 'Algeria', '阿尔及利亚');
INSERT INTO `area` VALUES (5, 'American Samoa', '美属萨摩亚');
INSERT INTO `area` VALUES (6, 'Andorra', '安道尔');
INSERT INTO `area` VALUES (7, 'Angola', '安哥拉');
INSERT INTO `area` VALUES (8, 'Anguilla', '安圭拉岛');
INSERT INTO `area` VALUES (9, 'Antarctica', '南极洲');
INSERT INTO `area` VALUES (10, 'Antigua and Barbuda', '安提瓜和巴布达');
INSERT INTO `area` VALUES (11, 'Argentina', '阿根廷');
INSERT INTO `area` VALUES (12, 'Armenia', '亚美尼亚');
INSERT INTO `area` VALUES (13, 'Aruba', '阿鲁巴岛');
INSERT INTO `area` VALUES (14, 'Australia', '澳大利亚');
INSERT INTO `area` VALUES (15, 'Austria', '奥地利');
INSERT INTO `area` VALUES (16, 'Azerbaijan', '阿塞拜疆');
INSERT INTO `area` VALUES (17, 'Bahamas, The', '巴哈马');
INSERT INTO `area` VALUES (18, 'Bahrain', '巴林');
INSERT INTO `area` VALUES (19, 'Bangladesh', '孟加拉国');
INSERT INTO `area` VALUES (20, 'Barbados', '巴巴多斯');
INSERT INTO `area` VALUES (21, 'Belarus', '白俄罗斯');
INSERT INTO `area` VALUES (22, 'Belgium', '比利时');
INSERT INTO `area` VALUES (23, 'Belize', '伯利兹');
INSERT INTO `area` VALUES (24, 'Benin', '贝宁');
INSERT INTO `area` VALUES (25, 'Bermuda', '百慕大');
INSERT INTO `area` VALUES (26, 'Bhutan', '不丹');
INSERT INTO `area` VALUES (27, 'Bolivia', '玻利维亚');
INSERT INTO `area` VALUES (28, 'Bosnia and Herzegovina', '波斯尼亚和黑塞哥维那');
INSERT INTO `area` VALUES (29, 'Botswana', '博茨瓦纳');
INSERT INTO `area` VALUES (30, 'Brazil', '巴西');
INSERT INTO `area` VALUES (31, 'British Indian Ocean Territory', '英属印度洋领地');
INSERT INTO `area` VALUES (32, 'British Virgin Islands', '英属维尔京群岛');
INSERT INTO `area` VALUES (33, 'Brunei', '文莱');
INSERT INTO `area` VALUES (34, 'Bulgaria', '保加利亚');
INSERT INTO `area` VALUES (35, 'Burkina Faso', '布基纳法索');
INSERT INTO `area` VALUES (36, 'Burma', '缅甸');
INSERT INTO `area` VALUES (37, 'Burundi', '布隆迪');
INSERT INTO `area` VALUES (38, 'Cambodia', '柬埔寨');
INSERT INTO `area` VALUES (39, 'Cameroon', '喀麦隆');
INSERT INTO `area` VALUES (40, 'Canada', '加拿大');
INSERT INTO `area` VALUES (41, 'Cape Verde', '佛得角');
INSERT INTO `area` VALUES (42, 'Cayman Islands', '开曼群岛');
INSERT INTO `area` VALUES (43, 'Central African Republic', '中非共和国');
INSERT INTO `area` VALUES (44, 'Chad', '乍得');
INSERT INTO `area` VALUES (45, 'Chile', '智利');
INSERT INTO `area` VALUES (46, 'Christmas Island', '圣诞岛');
INSERT INTO `area` VALUES (47, 'Clipperton Island', '克利珀顿岛');
INSERT INTO `area` VALUES (48, 'Cocos (Keeling) Islands', '科科斯（基林）群岛');
INSERT INTO `area` VALUES (49, 'Colombia', '哥伦比亚');
INSERT INTO `area` VALUES (50, 'Comoros', '科摩罗');
INSERT INTO `area` VALUES (51, 'Congo, Democratic Republic of the', '刚果民主共和国');
INSERT INTO `area` VALUES (52, 'Congo, Republic of the', '刚果共和国');
INSERT INTO `area` VALUES (53, 'Cook Islands', '库克群岛');
INSERT INTO `area` VALUES (54, 'Coral Sea Islands', '珊瑚海群岛');
INSERT INTO `area` VALUES (55, 'Costa Rica', '哥斯达黎加');
INSERT INTO `area` VALUES (56, 'Cote d\'Ivoire', '科特迪瓦');
INSERT INTO `area` VALUES (57, 'Croatia', '克罗地亚');
INSERT INTO `area` VALUES (58, 'Cuba', '古巴');
INSERT INTO `area` VALUES (59, 'Cyprus', '塞浦路斯');
INSERT INTO `area` VALUES (60, 'Czech Republic', '捷克共和国');
INSERT INTO `area` VALUES (61, 'Denmark', '丹麦');
INSERT INTO `area` VALUES (62, 'Djibouti', '吉布地');
INSERT INTO `area` VALUES (63, 'Dominica', '多米尼克');
INSERT INTO `area` VALUES (64, 'Dominican Republic', '多明尼加共和国');
INSERT INTO `area` VALUES (65, 'Ecuador', '厄瓜多尔');
INSERT INTO `area` VALUES (66, 'Egypt', '埃及');
INSERT INTO `area` VALUES (67, 'El Salvador', '萨尔瓦多');
INSERT INTO `area` VALUES (68, 'Equatorial Guinea', '赤道几内亚');
INSERT INTO `area` VALUES (69, 'Eritrea', '厄立特里亚');
INSERT INTO `area` VALUES (70, 'Estonia', '爱沙尼亚');
INSERT INTO `area` VALUES (71, 'Ethiopia', '埃塞俄比亚');
INSERT INTO `area` VALUES (72, 'Europa Island', '欧罗巴岛');
INSERT INTO `area` VALUES (73, 'Falkland Islands', '福克兰群岛');
INSERT INTO `area` VALUES (74, 'Faroe Islands', '法罗群岛');
INSERT INTO `area` VALUES (75, 'Fiji', '斐济');
INSERT INTO `area` VALUES (76, 'Finland', '芬兰');
INSERT INTO `area` VALUES (77, 'France', '法国');
INSERT INTO `area` VALUES (78, 'French Guiana', '法属圭亚那');
INSERT INTO `area` VALUES (79, 'French Polynesia', '法属波利尼西亚');
INSERT INTO `area` VALUES (80, 'Gabon', '加蓬');
INSERT INTO `area` VALUES (81, 'Gambia', '冈比亚');
INSERT INTO `area` VALUES (82, 'Georgia', '乔治亚');
INSERT INTO `area` VALUES (83, 'Germany', '德国');
INSERT INTO `area` VALUES (84, 'Ghana', '加纳');
INSERT INTO `area` VALUES (85, 'Gibraltar', '直布罗陀');
INSERT INTO `area` VALUES (86, 'Glorioso Islands', '格洛里厄斯群岛');
INSERT INTO `area` VALUES (87, 'Greece', '希腊');
INSERT INTO `area` VALUES (88, 'Greenland', '格陵兰');
INSERT INTO `area` VALUES (89, 'Grenada', '格林纳达');
INSERT INTO `area` VALUES (90, 'Guadeloupe', '瓜德罗普岛');
INSERT INTO `area` VALUES (91, 'Guam', '关岛');
INSERT INTO `area` VALUES (92, 'Guatemala', '危地马拉');
INSERT INTO `area` VALUES (93, 'Guernsey', '根西岛');
INSERT INTO `area` VALUES (94, 'Guinea', '几内亚');
INSERT INTO `area` VALUES (95, 'Guinea-Bissau', '几内亚比绍');
INSERT INTO `area` VALUES (96, 'Guyana', '圭亚那');
INSERT INTO `area` VALUES (97, 'Haiti', '海地');
INSERT INTO `area` VALUES (98, 'Holy See (Vatican City)', '罗马教廷（梵蒂冈城）');
INSERT INTO `area` VALUES (99, 'Honduras', '洪都拉斯');
INSERT INTO `area` VALUES (100, 'Hungary', '匈牙利');
INSERT INTO `area` VALUES (101, 'Iceland', '冰岛');
INSERT INTO `area` VALUES (102, 'India', '印度');
INSERT INTO `area` VALUES (103, 'Indonesia', '印度尼西亚');
INSERT INTO `area` VALUES (104, 'Iran', '伊朗');
INSERT INTO `area` VALUES (105, 'Iraq', '伊拉克');
INSERT INTO `area` VALUES (106, 'Ireland', '爱尔兰');
INSERT INTO `area` VALUES (107, 'Isle of Man', '马恩岛');
INSERT INTO `area` VALUES (108, 'Israel', '以色列');
INSERT INTO `area` VALUES (109, 'Italy', '意大利');
INSERT INTO `area` VALUES (110, 'Jamaica', '牙买加');
INSERT INTO `area` VALUES (111, 'Jan Mayen', '扬马延岛');
INSERT INTO `area` VALUES (112, 'Japan', '日本');
INSERT INTO `area` VALUES (113, 'Jersey', '泽西岛');
INSERT INTO `area` VALUES (114, 'Jordan', '约旦');
INSERT INTO `area` VALUES (115, 'Juan de Nova Island', '新胡安岛');
INSERT INTO `area` VALUES (116, 'Kazakhstan', '哈萨克斯坦');
INSERT INTO `area` VALUES (117, 'Kenya', '肯尼亚');
INSERT INTO `area` VALUES (118, 'Kiribati', '基里巴斯');
INSERT INTO `area` VALUES (119, 'Kuwait', '科威特');
INSERT INTO `area` VALUES (120, 'Kyrgyzstan', '吉尔吉斯斯坦');
INSERT INTO `area` VALUES (121, 'Laos', '老挝');
INSERT INTO `area` VALUES (122, 'Latvia', '拉脱维亚');
INSERT INTO `area` VALUES (123, 'Lebanon', '黎巴嫩');
INSERT INTO `area` VALUES (124, 'Lesotho', '莱索托');
INSERT INTO `area` VALUES (125, 'Liberia', '利比里亚');
INSERT INTO `area` VALUES (126, 'Libya', '利比亚');
INSERT INTO `area` VALUES (127, 'Liechtenstein', '列支敦士登');
INSERT INTO `area` VALUES (128, 'Lithuania', '立陶宛');
INSERT INTO `area` VALUES (129, 'Luxembourg', '卢森堡');
INSERT INTO `area` VALUES (130, 'Macedonia', '马其顿');
INSERT INTO `area` VALUES (131, 'Madagascar', '马达加斯加');
INSERT INTO `area` VALUES (132, 'Malawi', '马拉维');
INSERT INTO `area` VALUES (133, 'Malaysia', '马来西亚');
INSERT INTO `area` VALUES (134, 'Maldives', '马尔代夫');
INSERT INTO `area` VALUES (135, 'Mali', '马里');
INSERT INTO `area` VALUES (136, 'Malta', '马耳他');
INSERT INTO `area` VALUES (137, 'Marshall Islands', '马绍尔群岛');
INSERT INTO `area` VALUES (138, 'Martinique', '马提尼克岛');
INSERT INTO `area` VALUES (139, 'Mauritania', '毛里塔尼亚');
INSERT INTO `area` VALUES (140, 'Mauritius', '毛里求斯');
INSERT INTO `area` VALUES (141, 'Mayotte', '马约特岛');
INSERT INTO `area` VALUES (142, 'Mexico', '墨西哥');
INSERT INTO `area` VALUES (143, 'Micronesia, Federated States of', '密克罗尼西亚联邦');
INSERT INTO `area` VALUES (144, 'Moldova', '摩尔多瓦');
INSERT INTO `area` VALUES (145, 'Monaco', '摩纳哥');
INSERT INTO `area` VALUES (146, 'Mongolia', '蒙古');
INSERT INTO `area` VALUES (147, 'Montserrat', '蒙特塞拉特');
INSERT INTO `area` VALUES (148, 'Morocco', '摩洛哥');
INSERT INTO `area` VALUES (149, 'Mozambique', '莫桑比克');
INSERT INTO `area` VALUES (150, 'Namibia', '纳米比亚');
INSERT INTO `area` VALUES (151, 'Nauru', '瑙鲁');
INSERT INTO `area` VALUES (152, 'Navassa Island', '纳瓦萨岛');
INSERT INTO `area` VALUES (153, 'Nepal', '尼泊尔');
INSERT INTO `area` VALUES (154, 'Netherlands', '荷兰');
INSERT INTO `area` VALUES (155, 'Netherlands Antilles', '荷属安的列斯');
INSERT INTO `area` VALUES (156, 'New Caledonia', '新喀里多尼亚');
INSERT INTO `area` VALUES (157, 'New Zealand', '新西兰');
INSERT INTO `area` VALUES (158, 'Nicaragua', '尼加拉瓜');
INSERT INTO `area` VALUES (159, 'Niger', '尼日尔');
INSERT INTO `area` VALUES (160, 'Nigeria', '尼日利亚');
INSERT INTO `area` VALUES (161, 'Niue', '纽埃');
INSERT INTO `area` VALUES (162, 'Norfolk Island', '诺福克岛');
INSERT INTO `area` VALUES (163, 'North Korea', '朝鲜');
INSERT INTO `area` VALUES (164, 'Northern Mariana Islands', '北马里亚纳群岛');
INSERT INTO `area` VALUES (165, 'Norway', '挪威');
INSERT INTO `area` VALUES (166, 'Oman', '阿曼');
INSERT INTO `area` VALUES (167, 'Pakistan', '巴基斯坦');
INSERT INTO `area` VALUES (168, 'Palau', '帕劳');
INSERT INTO `area` VALUES (169, 'Panama', '巴拿马');
INSERT INTO `area` VALUES (170, 'Papua New Guinea', '巴布亚新几内亚');
INSERT INTO `area` VALUES (171, 'Paracel Islands', '西沙群岛');
INSERT INTO `area` VALUES (172, 'Paraguay', '巴拉圭');
INSERT INTO `area` VALUES (173, 'Peru', '秘鲁');
INSERT INTO `area` VALUES (174, 'Philippines', '菲律宾');
INSERT INTO `area` VALUES (175, 'Pitcairn Islands', '皮特凯恩群岛');
INSERT INTO `area` VALUES (176, 'Poland', '波兰');
INSERT INTO `area` VALUES (177, 'Portugal', '葡萄牙');
INSERT INTO `area` VALUES (178, 'Puerto Rico', '波多黎各');
INSERT INTO `area` VALUES (179, 'Qatar', '卡塔尔');
INSERT INTO `area` VALUES (180, 'Reunion', '留尼汪');
INSERT INTO `area` VALUES (181, 'Romania', '罗马尼亚');
INSERT INTO `area` VALUES (182, 'Russia', '俄罗斯');
INSERT INTO `area` VALUES (183, 'Rwanda', '卢旺达');
INSERT INTO `area` VALUES (184, 'Saint Helena', '圣赫勒拿岛');
INSERT INTO `area` VALUES (185, 'Saint Kitts and Nevis', '圣基茨和尼维斯');
INSERT INTO `area` VALUES (186, 'Saint Lucia', '圣卢西亚岛');
INSERT INTO `area` VALUES (187, 'Saint Pierre and Miquelon', '圣皮埃尔和密克隆群岛');
INSERT INTO `area` VALUES (188, 'Saint Vincent and the Grenadines', '圣文森特和格林纳丁斯');
INSERT INTO `area` VALUES (189, 'Samoa', '萨摩亚');
INSERT INTO `area` VALUES (190, 'San Marino', '圣马力诺');
INSERT INTO `area` VALUES (191, 'Sao Tome and Principe', '圣多美和普林西比');
INSERT INTO `area` VALUES (192, 'Saudi Arabia', '沙特阿拉伯');
INSERT INTO `area` VALUES (193, 'Senegal', '塞内加尔');
INSERT INTO `area` VALUES (194, 'Serbia and Montenegro', '塞尔维亚和黑山');
INSERT INTO `area` VALUES (195, 'Seychelles', '塞舌尔群岛');
INSERT INTO `area` VALUES (196, 'Sierra Leone', '塞拉利昂');
INSERT INTO `area` VALUES (197, 'Singapore', '新加坡');
INSERT INTO `area` VALUES (198, 'Slovakia', '斯洛伐克');
INSERT INTO `area` VALUES (199, 'Slovenia', '斯洛文尼亚');
INSERT INTO `area` VALUES (200, 'Solomon Islands', '所罗门群岛');
INSERT INTO `area` VALUES (201, 'Somalia', '索马里');
INSERT INTO `area` VALUES (202, 'South Africa', '南非');
INSERT INTO `area` VALUES (203, 'South Korea', '韩国');
INSERT INTO `area` VALUES (204, 'Spain', '西班牙');
INSERT INTO `area` VALUES (205, 'Spratly Islands', '南沙群岛');
INSERT INTO `area` VALUES (206, 'Sri Lanka', '斯里兰卡');
INSERT INTO `area` VALUES (207, 'Sudan', '苏丹');
INSERT INTO `area` VALUES (208, 'Suriname', '苏里南');
INSERT INTO `area` VALUES (209, 'Svalbard', '斯瓦尔巴群岛');
INSERT INTO `area` VALUES (210, 'Swaziland', '斯威士兰');
INSERT INTO `area` VALUES (211, 'Sweden', '瑞典');
INSERT INTO `area` VALUES (212, 'Switzerland', '瑞士');
INSERT INTO `area` VALUES (213, 'Syria', '叙利亚');
INSERT INTO `area` VALUES (214, 'Tajikistan', '塔吉克斯坦');
INSERT INTO `area` VALUES (215, 'Tanzania', '坦桑尼亚');
INSERT INTO `area` VALUES (216, 'Thailand', '泰国');
INSERT INTO `area` VALUES (217, 'Timor-Leste', '东帝汶');
INSERT INTO `area` VALUES (218, 'Togo', '多哥');
INSERT INTO `area` VALUES (219, 'Tokelau', '托克劳');
INSERT INTO `area` VALUES (220, 'Tonga', '汤加');
INSERT INTO `area` VALUES (221, 'Trinidad and Tobago', '特立尼达和多巴哥');
INSERT INTO `area` VALUES (222, 'Tromelin Island', '特罗姆兰岛');
INSERT INTO `area` VALUES (223, 'Tunisia', '突尼斯');
INSERT INTO `area` VALUES (224, 'Turkey', '土耳其');
INSERT INTO `area` VALUES (225, 'Turkmenistan', '土库曼斯坦');
INSERT INTO `area` VALUES (226, 'Turks and Caicos Islands', '特克斯和凯科斯群岛');
INSERT INTO `area` VALUES (227, 'Tuvalu', '图瓦卢');
INSERT INTO `area` VALUES (228, 'Uganda', '乌干达');
INSERT INTO `area` VALUES (229, 'Ukraine', '乌克兰');
INSERT INTO `area` VALUES (230, 'United Arab Emirates', '阿拉伯联合酋长国');
INSERT INTO `area` VALUES (231, 'United Kingdom', '英国');
INSERT INTO `area` VALUES (232, 'United States', '美国');
INSERT INTO `area` VALUES (233, 'Uruguay', '乌拉圭');
INSERT INTO `area` VALUES (234, 'Uzbekistan', '乌兹别克斯坦');
INSERT INTO `area` VALUES (235, 'Vanuatu', '瓦努阿图');
INSERT INTO `area` VALUES (236, 'Venezuela', '委内瑞拉');
INSERT INTO `area` VALUES (237, 'Vietnam', '越南');
INSERT INTO `area` VALUES (238, 'Virgin Islands', '维尔京群岛');
INSERT INTO `area` VALUES (239, 'Wake Island', '威克岛');
INSERT INTO `area` VALUES (240, 'Wallis and Futuna', '瓦利斯和富图纳群岛');
INSERT INTO `area` VALUES (241, 'Western Sahara', '西撒哈拉');
INSERT INTO `area` VALUES (242, 'Yemen', '也门');
INSERT INTO `area` VALUES (243, 'Zambia', '赞比亚');
INSERT INTO `area` VALUES (244, 'Zimbabwe', '津巴布韦');

-- ----------------------------
-- Table structure for article_examine_log
-- ----------------------------
DROP TABLE IF EXISTS `article_examine_log`;
CREATE TABLE `article_examine_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `examine_state` int(6) NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id_key`(`article_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_examine_log
-- ----------------------------

-- ----------------------------
-- Table structure for article_hot
-- ----------------------------
DROP TABLE IF EXISTS `article_hot`;
CREATE TABLE `article_hot`  (
  `hot_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `comment_num` bigint(20) NOT NULL,
  `star_num` bigint(20) NOT NULL,
  `liked_num` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`hot_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_hot
-- ----------------------------
INSERT INTO `article_hot` VALUES (1, 'XSA0000000000000000000000000000000000000000000000000012637035298816', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (2, 'XSA0000000000000000000000000000000000000000000000000012637035298817', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (3, 'XSA0000000000000000000000000000000000000000000000000012637035298818', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (4, 'XSA0000000000000000000000000000000000000000000000000012637035298819', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (5, 'XSA0000000000000000000000000000000000000000000000000012637035298820', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (6, 'XSA0000000000000000000000000000000000000000000000000012660410155008', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (7, 'XSA0000000000000000000000000000000000000000000000000012660410155009', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (8, 'XSA0000000000000000000000000000000000000000000000000012660410155010', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (9, 'XSA0000000000000000000000000000000000000000000000000012660410155011', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (10, 'XSA0000000000000000000000000000000000000000000000000012660410155012', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (11, 'XSA0000000000000000000000000000000000000000000000000012666986823680', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (12, 'XSA0000000000000000000000000000000000000000000000000012666986823681', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (13, 'XSA0000000000000000000000000000000000000000000000000012666986823682', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (14, 'XSA0000000000000000000000000000000000000000000000000012666986823683', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (15, 'XSA0000000000000000000000000000000000000000000000000012666986823684', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (16, 'XSA0000000000000000000000000000000000000000000000000012675438346240', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (17, 'XSA0000000000000000000000000000000000000000000000000012675438346241', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (18, 'XSA0000000000000000000000000000000000000000000000000012675438346242', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (19, 'XSA0000000000000000000000000000000000000000000000000012675438346243', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (20, 'XSA0000000000000000000000000000000000000000000000000012675438346244', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (21, 'XSA0000000000000000000000000000000000000000000000000012705050132480', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (22, 'XSA0000000000000000000000000000000000000000000000000012705050132481', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (23, 'XSA0000000000000000000000000000000000000000000000000012705050132482', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (24, 'XSA0000000000000000000000000000000000000000000000000012705050132483', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');
INSERT INTO `article_hot` VALUES (25, 'XSA0000000000000000000000000000000000000000000000000012705050132484', 0, 0, 0, '2024-11-13 00:00:00', '2024-11-13 00:00:00');

-- ----------------------------
-- Table structure for article_state
-- ----------------------------
DROP TABLE IF EXISTS `article_state`;
CREATE TABLE `article_state`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_state
-- ----------------------------
INSERT INTO `article_state` VALUES (1, '正常');
INSERT INTO `article_state` VALUES (2, '违规');
INSERT INTO `article_state` VALUES (3, '热点');
INSERT INTO `article_state` VALUES (4, '限流');
INSERT INTO `article_state` VALUES (5, '下架');
INSERT INTO `article_state` VALUES (6, '待审核');

-- ----------------------------
-- Table structure for article_style
-- ----------------------------
DROP TABLE IF EXISTS `article_style`;
CREATE TABLE `article_style`  (
  `id` int(11) NOT NULL,
  `style_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article_style
-- ----------------------------
INSERT INTO `article_style` VALUES (0, '无');
INSERT INTO `article_style` VALUES (1, '生活');
INSERT INTO `article_style` VALUES (2, '教育');
INSERT INTO `article_style` VALUES (3, '科技');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `birth` date NULL DEFAULT NULL,
  `id_number` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `area_id` int(11) NOT NULL,
  `state_id` int(11) NOT NULL,
  `icon_path` longtext CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `create_time` datetime NOT NULL,
  `level` int(11) NOT NULL,
  `points_level_id` int(11) NOT NULL DEFAULT 1,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `email_key`(`email`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (1, 'Wilsam', 'Szeto', 'xxsrkk@gmail.com', '0b210fc07ba25b6710e170afc473a140b9f9ebbcdd979d7bbab61a6ac66884cb70b4da3a6bd07c27', '2006-06-28', 'XS202409180005', 1, 0, 'http://120.24.88.92:9000/xs-assistant-bucket/icon-2.jpg', '2024-09-18 19:02:05', 1, 1, NULL);

-- ----------------------------
-- Table structure for customer_level
-- ----------------------------
DROP TABLE IF EXISTS `customer_level`;
CREATE TABLE `customer_level`  (
  `id` int(11) NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of customer_level
-- ----------------------------
INSERT INTO `customer_level` VALUES (1, 'admin');
INSERT INTO `customer_level` VALUES (2, 'account');
INSERT INTO `customer_level` VALUES (3, 'x_account');

-- ----------------------------
-- Table structure for customer_points_level
-- ----------------------------
DROP TABLE IF EXISTS `customer_points_level`;
CREATE TABLE `customer_points_level`  (
  `points_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `points_level` int(11) NOT NULL,
  `points` int(11) NOT NULL,
  PRIMARY KEY (`points_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_points_level
-- ----------------------------

-- ----------------------------
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon`  (
  `Id` int(11) NOT NULL,
  `Name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Acrpnym` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `Logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of icon
-- ----------------------------
INSERT INTO `icon` VALUES (1, 'XS-Account', 'XS-Assistant the account', NULL);

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `background` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `author_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `article_id` varchar(67) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `state_id` int(11) NOT NULL,
  `style_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `AuthorId`(`author_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO `note` VALUES (1, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012637035298816', 6, 1, '2024-11-13 16:07:56', '2024-11-13 16:07:56');
INSERT INTO `note` VALUES (2, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012637035298817', 6, 1, '2024-11-13 16:07:56', '2024-11-13 16:07:56');
INSERT INTO `note` VALUES (3, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012637035298818', 6, 1, '2024-11-13 16:07:56', '2024-11-13 16:07:56');
INSERT INTO `note` VALUES (4, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012637035298819', 6, 1, '2024-11-13 16:07:56', '2024-11-13 16:07:56');
INSERT INTO `note` VALUES (5, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012637035298820', 6, 1, '2024-11-13 16:07:56', '2024-11-13 16:07:56');
INSERT INTO `note` VALUES (6, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012660410155008', 6, 1, '2024-11-13 16:08:01', '2024-11-13 16:08:01');
INSERT INTO `note` VALUES (7, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012660410155009', 6, 1, '2024-11-13 16:08:01', '2024-11-13 16:08:01');
INSERT INTO `note` VALUES (8, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012660410155010', 6, 1, '2024-11-13 16:08:01', '2024-11-13 16:08:01');
INSERT INTO `note` VALUES (9, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012660410155011', 6, 1, '2024-11-13 16:08:01', '2024-11-13 16:08:01');
INSERT INTO `note` VALUES (10, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012660410155012', 6, 1, '2024-11-13 16:08:01', '2024-11-13 16:08:01');
INSERT INTO `note` VALUES (11, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012666986823680', 6, 1, '2024-11-13 16:08:03', '2024-11-13 16:08:03');
INSERT INTO `note` VALUES (12, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012666986823681', 6, 1, '2024-11-13 16:08:03', '2024-11-13 16:08:03');
INSERT INTO `note` VALUES (13, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012666986823682', 6, 1, '2024-11-13 16:08:03', '2024-11-13 16:08:03');
INSERT INTO `note` VALUES (14, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012666986823683', 6, 1, '2024-11-13 16:08:03', '2024-11-13 16:08:03');
INSERT INTO `note` VALUES (15, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012666986823684', 6, 1, '2024-11-13 16:08:03', '2024-11-13 16:08:03');
INSERT INTO `note` VALUES (16, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012675438346240', 6, 1, '2024-11-13 16:08:05', '2024-11-13 16:08:05');
INSERT INTO `note` VALUES (17, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012675438346241', 6, 1, '2024-11-13 16:08:05', '2024-11-13 16:08:05');
INSERT INTO `note` VALUES (18, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012675438346242', 6, 1, '2024-11-13 16:08:05', '2024-11-13 16:08:05');
INSERT INTO `note` VALUES (19, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012675438346243', 6, 1, '2024-11-13 16:08:05', '2024-11-13 16:08:05');
INSERT INTO `note` VALUES (20, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012675438346244', 6, 1, '2024-11-13 16:08:05', '2024-11-13 16:08:05');
INSERT INTO `note` VALUES (21, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012705050132480', 6, 1, '2024-11-13 16:08:12', '2024-11-13 16:08:12');
INSERT INTO `note` VALUES (22, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012705050132481', 6, 1, '2024-11-13 16:08:12', '2024-11-13 16:08:12');
INSERT INTO `note` VALUES (23, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012705050132482', 6, 1, '2024-11-13 16:08:12', '2024-11-13 16:08:12');
INSERT INTO `note` VALUES (24, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012705050132483', 6, 1, '2024-11-13 16:08:12', '2024-11-13 16:08:12');
INSERT INTO `note` VALUES (25, 'Background 5', 'Illustration 1', 'Logo 2', 'XS202409180005', 'XSA0000000000000000000000000000000000000000000000000012705050132484', 6, 1, '2024-11-13 16:08:12', '2024-11-13 16:08:12');

-- ----------------------------
-- Table structure for points_level
-- ----------------------------
DROP TABLE IF EXISTS `points_level`;
CREATE TABLE `points_level`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `min_points` int(11) NOT NULL,
  `max_points` int(11) NOT NULL,
  `privileges` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT '等级权限描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of points_level
-- ----------------------------
INSERT INTO `points_level` VALUES (1, '无能力者', 0, 999, NULL);
INSERT INTO `points_level` VALUES (2, '异能力者', 1000, 2999, NULL);
INSERT INTO `points_level` VALUES (3, '强能力者', 3000, 6999, NULL);
INSERT INTO `points_level` VALUES (4, '大能力者', 7000, 11999, NULL);
INSERT INTO `points_level` VALUES (5, '超能力者', 12000, 999999, NULL);
INSERT INTO `points_level` VALUES (6, '绝对能力者', 100000, 9999999, NULL);

-- ----------------------------
-- Table structure for sql_operate
-- ----------------------------
DROP TABLE IF EXISTS `sql_operate`;
CREATE TABLE `sql_operate`  (
  `id` int(11) NOT NULL,
  `operate` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sql_operate
-- ----------------------------
INSERT INTO `sql_operate` VALUES (1, 'update');
INSERT INTO `sql_operate` VALUES (2, 'insert');
INSERT INTO `sql_operate` VALUES (3, 'delete');

-- ----------------------------
-- Table structure for system_info
-- ----------------------------
DROP TABLE IF EXISTS `system_info`;
CREATE TABLE `system_info`  (
  `customer_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `system` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `computer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `sys_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `last_time` datetime NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`sys_id`) USING BTREE,
  INDEX `index_sys_customer_id`(`customer_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_info
-- ----------------------------
INSERT INTO `system_info` VALUES ('XS202409180005', 'Windows 8', 'DESKTOP-I1B7ISU', 'XS202409180005S0000', '2024-12-12 15:04:12', 1);

-- ----------------------------
-- Table structure for system_info_history
-- ----------------------------
DROP TABLE IF EXISTS `system_info_history`;
CREATE TABLE `system_info_history`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `system` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `computer_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_info_history
-- ----------------------------

-- ----------------------------
-- Table structure for system_info_log
-- ----------------------------
DROP TABLE IF EXISTS `system_info_log`;
CREATE TABLE `system_info_log`  (
  `system_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `operate_type` int(11) NOT NULL,
  `old_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `new_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  PRIMARY KEY (`system_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_info_log
-- ----------------------------
INSERT INTO `system_info_log` VALUES ('XS202409180005S0000', '2024-12-12 15:04:11', '2024-12-12 15:04:11', 1, NULL, 'XS202409180005S0000');

-- ----------------------------
-- View structure for customer_no_password
-- ----------------------------
DROP VIEW IF EXISTS `customer_no_password`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `customer_no_password` AS select `customer`.`id` AS `id`,`customer`.`first_name` AS `first_name`,`customer`.`last_name` AS `last_name`,`customer`.`email` AS `email`,`customer`.`birth` AS `birth`,`customer`.`id_number` AS `id_number`,`customer`.`area_id` AS `area_id`,`customer`.`state_id` AS `state_id`,`customer`.`level` AS `level`,`customer`.`update_time` AS `update_time`,`customer`.`create_time` AS `create_time` from `customer`;

-- ----------------------------
-- View structure for note_v_1
-- ----------------------------
DROP VIEW IF EXISTS `note_v_1`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `note_v_1` AS select `note`.`id` AS `id`,`note`.`author_id` AS `author_id`,`note`.`article_id` AS `article_id`,`note`.`create_time` AS `create_time` from `note` where (`note`.`id` <= 10);

-- ----------------------------
-- Procedure structure for p1
-- ----------------------------
DROP PROCEDURE IF EXISTS `p1`;
delimiter ;;
CREATE PROCEDURE `p1`()
begin
	select count(*) from note;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table customer_points_level
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_customer_points_level_update`;
delimiter ;;
CREATE TRIGGER `tri_customer_points_level_update` AFTER UPDATE ON `customer_points_level` FOR EACH ROW begin
	declare new_level_id int;
	select id into new_level_id
	from points_level
	where new.points between min_points and max_points
	LIMIT 1;
	if (new.points_level != new_level_id)
	then
		update customer_points_level
		set points_level = new_level_id
		WHERE points_id = new.points_id;
	end if;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table system_info
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_update_system_info`;
delimiter ;;
CREATE TRIGGER `tri_update_system_info` BEFORE UPDATE ON `system_info` FOR EACH ROW begin
	declare history_id varchar(20) DEFAULT '';
	declare update_count int default 0;
	IF
		(old.system != new.system) OR (old.computer_name != new.computer_name) OR (old.version != new.version)
		THEN
			select count(*) 
			into update_count 
			from system_info_log 
			where system_id = old.sys_id
				and update_time >= CURDATE() 
				and update_time < CURDATE() + INTERVAL 1 DAY;
			 
			set history_id = CONCAT('XSSH',DATE_FORMAT(CURDATE(),'%Y%m%d'),LPAD(update_count + 1,4,'0'));
			insert into system_info_history (id,`system`,computer_name,version)
			VALUES(history_id,old.system,old.computer_name,old.version);
			
			insert into system_info_log (system_id, create_time, update_time,operate_type,old_id,new_id) 
			VALUES(new.sys_id, now(), now(),2,history_id,new.sys_id);
	END IF;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table system_info
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_insert_system_info`;
delimiter ;;
CREATE TRIGGER `tri_insert_system_info` AFTER INSERT ON `system_info` FOR EACH ROW begin
	insert into system_info_log (system_id, create_time, update_time,operate_type,old_id,new_id) 
	VALUES(new.sys_id, now(), now(),1,null,new.sys_id);
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table system_info
-- ----------------------------
DROP TRIGGER IF EXISTS `tri_delete_system_info`;
delimiter ;;
CREATE TRIGGER `tri_delete_system_info` AFTER DELETE ON `system_info` FOR EACH ROW begin
	declare history_id varchar(20) DEFAULT '';
	declare update_count int default 0;
	
	select count(*) 
	into update_count 
	from system_info_log 
	where system_id = old.sys_id
		and update_time >= CURDATE() 
		and update_time < CURDATE() + INTERVAL 1 DAY;
	 
	set history_id = CONCAT('XSSH',DATE_FORMAT(CURDATE(),'%Y%m%d'),LPAD(update_count + 1,4,'0'));

	insert into system_info_history (id,`system`,computer_name,version)
	VALUES(history_id,old.system,old.computer_name,old.version);
	
	insert into system_info_log (system_id, create_time, update_time,operate_type,old_id,new_id) 
	VALUES(old.sys_id, now(), now(),3,history_id,null);
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
