/*
 Navicat Premium Data Transfer

 Source Server         : Local-Mysql
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : xs

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 28/04/2024 15:34:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for area
-- ----------------------------
DROP TABLE IF EXISTS `area`;
CREATE TABLE `area` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `AreaName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `AreaNameChinese` varchar(255) COLLATE utf8mb3_bin NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=245 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of area
-- ----------------------------
BEGIN;
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (1, 'China', '中国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (2, 'Afghanistan', '阿富汗');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (3, 'Albania', '阿尔巴尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (4, 'Algeria', '阿尔及利亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (5, 'American Samoa', '美属萨摩亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (6, 'Andorra', '安道尔');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (7, 'Angola', '安哥拉');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (8, 'Anguilla', '安圭拉岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (9, 'Antarctica', '南极洲');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (10, 'Antigua and Barbuda', '安提瓜和巴布达');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (11, 'Argentina', '阿根廷');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (12, 'Armenia', '亚美尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (13, 'Aruba', '阿鲁巴岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (14, 'Australia', '澳大利亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (15, 'Austria', '奥地利');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (16, 'Azerbaijan', '阿塞拜疆');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (17, 'Bahamas, The', '巴哈马');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (18, 'Bahrain', '巴林');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (19, 'Bangladesh', '孟加拉国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (20, 'Barbados', '巴巴多斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (21, 'Belarus', '白俄罗斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (22, 'Belgium', '比利时');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (23, 'Belize', '伯利兹');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (24, 'Benin', '贝宁');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (25, 'Bermuda', '百慕大');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (26, 'Bhutan', '不丹');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (27, 'Bolivia', '玻利维亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (28, 'Bosnia and Herzegovina', '波斯尼亚和黑塞哥维那');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (29, 'Botswana', '博茨瓦纳');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (30, 'Brazil', '巴西');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (31, 'British Indian Ocean Territory', '英属印度洋领地');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (32, 'British Virgin Islands', '英属维尔京群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (33, 'Brunei', '文莱');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (34, 'Bulgaria', '保加利亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (35, 'Burkina Faso', '布基纳法索');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (36, 'Burma', '缅甸');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (37, 'Burundi', '布隆迪');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (38, 'Cambodia', '柬埔寨');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (39, 'Cameroon', '喀麦隆');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (40, 'Canada', '加拿大');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (41, 'Cape Verde', '佛得角');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (42, 'Cayman Islands', '开曼群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (43, 'Central African Republic', '中非共和国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (44, 'Chad', '乍得');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (45, 'Chile', '智利');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (46, 'Christmas Island', '圣诞岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (47, 'Clipperton Island', '克利珀顿岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (48, 'Cocos (Keeling) Islands', '科科斯（基林）群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (49, 'Colombia', '哥伦比亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (50, 'Comoros', '科摩罗');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (51, 'Congo, Democratic Republic of the', '刚果民主共和国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (52, 'Congo, Republic of the', '刚果共和国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (53, 'Cook Islands', '库克群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (54, 'Coral Sea Islands', '珊瑚海群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (55, 'Costa Rica', '哥斯达黎加');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (56, 'Cote d\'Ivoire', '科特迪瓦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (57, 'Croatia', '克罗地亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (58, 'Cuba', '古巴');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (59, 'Cyprus', '塞浦路斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (60, 'Czech Republic', '捷克共和国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (61, 'Denmark', '丹麦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (62, 'Djibouti', '吉布地');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (63, 'Dominica', '多米尼克');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (64, 'Dominican Republic', '多明尼加共和国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (65, 'Ecuador', '厄瓜多尔');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (66, 'Egypt', '埃及');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (67, 'El Salvador', '萨尔瓦多');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (68, 'Equatorial Guinea', '赤道几内亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (69, 'Eritrea', '厄立特里亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (70, 'Estonia', '爱沙尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (71, 'Ethiopia', '埃塞俄比亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (72, 'Europa Island', '欧罗巴岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (73, 'Falkland Islands', '福克兰群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (74, 'Faroe Islands', '法罗群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (75, 'Fiji', '斐济');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (76, 'Finland', '芬兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (77, 'France', '法国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (78, 'French Guiana', '法属圭亚那');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (79, 'French Polynesia', '法属波利尼西亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (80, 'Gabon', '加蓬');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (81, 'Gambia', '冈比亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (82, 'Georgia', '乔治亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (83, 'Germany', '德国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (84, 'Ghana', '加纳');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (85, 'Gibraltar', '直布罗陀');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (86, 'Glorioso Islands', '格洛里厄斯群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (87, 'Greece', '希腊');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (88, 'Greenland', '格陵兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (89, 'Grenada', '格林纳达');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (90, 'Guadeloupe', '瓜德罗普岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (91, 'Guam', '关岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (92, 'Guatemala', '危地马拉');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (93, 'Guernsey', '根西岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (94, 'Guinea', '几内亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (95, 'Guinea-Bissau', '几内亚比绍');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (96, 'Guyana', '圭亚那');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (97, 'Haiti', '海地');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (98, 'Holy See (Vatican City)', '罗马教廷（梵蒂冈城）');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (99, 'Honduras', '洪都拉斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (100, 'Hungary', '匈牙利');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (101, 'Iceland', '冰岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (102, 'India', '印度');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (103, 'Indonesia', '印度尼西亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (104, 'Iran', '伊朗');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (105, 'Iraq', '伊拉克');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (106, 'Ireland', '爱尔兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (107, 'Isle of Man', '马恩岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (108, 'Israel', '以色列');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (109, 'Italy', '意大利');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (110, 'Jamaica', '牙买加');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (111, 'Jan Mayen', '扬马延岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (112, 'Japan', '日本');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (113, 'Jersey', '泽西岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (114, 'Jordan', '约旦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (115, 'Juan de Nova Island', '新胡安岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (116, 'Kazakhstan', '哈萨克斯坦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (117, 'Kenya', '肯尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (118, 'Kiribati', '基里巴斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (119, 'Kuwait', '科威特');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (120, 'Kyrgyzstan', '吉尔吉斯斯坦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (121, 'Laos', '老挝');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (122, 'Latvia', '拉脱维亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (123, 'Lebanon', '黎巴嫩');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (124, 'Lesotho', '莱索托');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (125, 'Liberia', '利比里亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (126, 'Libya', '利比亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (127, 'Liechtenstein', '列支敦士登');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (128, 'Lithuania', '立陶宛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (129, 'Luxembourg', '卢森堡');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (130, 'Macedonia', '马其顿');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (131, 'Madagascar', '马达加斯加');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (132, 'Malawi', '马拉维');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (133, 'Malaysia', '马来西亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (134, 'Maldives', '马尔代夫');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (135, 'Mali', '马里');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (136, 'Malta', '马耳他');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (137, 'Marshall Islands', '马绍尔群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (138, 'Martinique', '马提尼克岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (139, 'Mauritania', '毛里塔尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (140, 'Mauritius', '毛里求斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (141, 'Mayotte', '马约特岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (142, 'Mexico', '墨西哥');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (143, 'Micronesia, Federated States of', '密克罗尼西亚联邦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (144, 'Moldova', '摩尔多瓦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (145, 'Monaco', '摩纳哥');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (146, 'Mongolia', '蒙古');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (147, 'Montserrat', '蒙特塞拉特');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (148, 'Morocco', '摩洛哥');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (149, 'Mozambique', '莫桑比克');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (150, 'Namibia', '纳米比亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (151, 'Nauru', '瑙鲁');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (152, 'Navassa Island', '纳瓦萨岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (153, 'Nepal', '尼泊尔');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (154, 'Netherlands', '荷兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (155, 'Netherlands Antilles', '荷属安的列斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (156, 'New Caledonia', '新喀里多尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (157, 'New Zealand', '新西兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (158, 'Nicaragua', '尼加拉瓜');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (159, 'Niger', '尼日尔');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (160, 'Nigeria', '尼日利亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (161, 'Niue', '纽埃');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (162, 'Norfolk Island', '诺福克岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (163, 'North Korea', '朝鲜');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (164, 'Northern Mariana Islands', '北马里亚纳群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (165, 'Norway', '挪威');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (166, 'Oman', '阿曼');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (167, 'Pakistan', '巴基斯坦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (168, 'Palau', '帕劳');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (169, 'Panama', '巴拿马');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (170, 'Papua New Guinea', '巴布亚新几内亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (171, 'Paracel Islands', '西沙群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (172, 'Paraguay', '巴拉圭');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (173, 'Peru', '秘鲁');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (174, 'Philippines', '菲律宾');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (175, 'Pitcairn Islands', '皮特凯恩群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (176, 'Poland', '波兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (177, 'Portugal', '葡萄牙');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (178, 'Puerto Rico', '波多黎各');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (179, 'Qatar', '卡塔尔');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (180, 'Reunion', '留尼汪');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (181, 'Romania', '罗马尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (182, 'Russia', '俄罗斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (183, 'Rwanda', '卢旺达');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (184, 'Saint Helena', '圣赫勒拿岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (185, 'Saint Kitts and Nevis', '圣基茨和尼维斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (186, 'Saint Lucia', '圣卢西亚岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (187, 'Saint Pierre and Miquelon', '圣皮埃尔和密克隆群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (188, 'Saint Vincent and the Grenadines', '圣文森特和格林纳丁斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (189, 'Samoa', '萨摩亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (190, 'San Marino', '圣马力诺');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (191, 'Sao Tome and Principe', '圣多美和普林西比');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (192, 'Saudi Arabia', '沙特阿拉伯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (193, 'Senegal', '塞内加尔');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (194, 'Serbia and Montenegro', '塞尔维亚和黑山');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (195, 'Seychelles', '塞舌尔群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (196, 'Sierra Leone', '塞拉利昂');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (197, 'Singapore', '新加坡');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (198, 'Slovakia', '斯洛伐克');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (199, 'Slovenia', '斯洛文尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (200, 'Solomon Islands', '所罗门群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (201, 'Somalia', '索马里');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (202, 'South Africa', '南非');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (203, 'South Korea', '韩国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (204, 'Spain', '西班牙');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (205, 'Spratly Islands', '南沙群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (206, 'Sri Lanka', '斯里兰卡');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (207, 'Sudan', '苏丹');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (208, 'Suriname', '苏里南');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (209, 'Svalbard', '斯瓦尔巴群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (210, 'Swaziland', '斯威士兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (211, 'Sweden', '瑞典');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (212, 'Switzerland', '瑞士');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (213, 'Syria', '叙利亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (214, 'Tajikistan', '塔吉克斯坦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (215, 'Tanzania', '坦桑尼亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (216, 'Thailand', '泰国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (217, 'Timor-Leste', '东帝汶');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (218, 'Togo', '多哥');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (219, 'Tokelau', '托克劳');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (220, 'Tonga', '汤加');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (221, 'Trinidad and Tobago', '特立尼达和多巴哥');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (222, 'Tromelin Island', '特罗姆兰岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (223, 'Tunisia', '突尼斯');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (224, 'Turkey', '土耳其');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (225, 'Turkmenistan', '土库曼斯坦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (226, 'Turks and Caicos Islands', '特克斯和凯科斯群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (227, 'Tuvalu', '图瓦卢');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (228, 'Uganda', '乌干达');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (229, 'Ukraine', '乌克兰');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (230, 'United Arab Emirates', '阿拉伯联合酋长国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (231, 'United Kingdom', '英国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (232, 'United States', '美国');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (233, 'Uruguay', '乌拉圭');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (234, 'Uzbekistan', '乌兹别克斯坦');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (235, 'Vanuatu', '瓦努阿图');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (236, 'Venezuela', '委内瑞拉');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (237, 'Vietnam', '越南');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (238, 'Virgin Islands', '维尔京群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (239, 'Wake Island', '威克岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (240, 'Wallis and Futuna', '瓦利斯和富图纳群岛');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (241, 'Western Sahara', '西撒哈拉');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (242, 'Yemen', '也门');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (243, 'Zambia', '赞比亚');
INSERT INTO `area` (`Id`, `AreaName`, `AreaNameChinese`) VALUES (244, 'Zimbabwe', '津巴布韦');
COMMIT;

-- ----------------------------
-- Table structure for article_state
-- ----------------------------
DROP TABLE IF EXISTS `article_state`;
CREATE TABLE `article_state` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stateName` varchar(20) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of article_state
-- ----------------------------
BEGIN;
INSERT INTO `article_state` (`id`, `stateName`) VALUES (1, '正常');
INSERT INTO `article_state` (`id`, `stateName`) VALUES (2, '违规');
INSERT INTO `article_state` (`id`, `stateName`) VALUES (3, '热点');
INSERT INTO `article_state` (`id`, `stateName`) VALUES (4, '限流');
INSERT INTO `article_state` (`id`, `stateName`) VALUES (5, '下架');
COMMIT;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `Id` bigint NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `LastName` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Birth` date DEFAULT NULL,
  `IdNumber` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `AreaId` int NOT NULL,
  `StateId` int NOT NULL,
  `CreateTime` datetime NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  KEY `Area` (`AreaId`) USING BTREE,
  KEY `state_id_key` (`StateId`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of customer
-- ----------------------------
BEGIN;
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (1, 'Xiao', 'Shuai', '1298372143@qq.com', '4b40f76076748bd8c2634a85287b25a0d92a442141102b26a58d3b8f65b590ecbffa0cd07eb703bc', '2012-06-28', 'XS0000000001', 1, 0, '2024-04-22 19:56:50');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (2, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1, 0, '2024-04-22 19:56:55');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (3, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1, 0, '2024-04-22 19:56:58');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (4, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1, 0, '2024-04-22 19:57:00');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (5, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1, 0, '2024-04-22 19:57:03');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (6, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1, 0, '2024-04-22 19:57:06');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (7, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1, 0, '2024-04-22 19:57:08');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (8, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', '202401280000', 1, 0, '2024-04-22 19:57:11');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (9, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', 'XS202401280000', 1, 0, '2024-04-22 19:57:14');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (10, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', 'XS202401280001', 1, 0, '2024-04-22 19:57:16');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (11, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', 'XS202401280001', 1, 0, '2024-04-22 19:57:18');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (12, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', 'XS202401280001', 1, 0, '2024-04-22 19:57:21');
INSERT INTO `customer` (`Id`, `FirstName`, `LastName`, `Email`, `Password`, `Birth`, `IdNumber`, `AreaId`, `StateId`, `CreateTime`) VALUES (13, 'Watson', 'Yu', 'xxsrkk@gmail.com', '5567789', '2004-06-28', 'XS202401280003', 1, 0, '2024-04-22 19:57:24');
COMMIT;

-- ----------------------------
-- Table structure for icon
-- ----------------------------
DROP TABLE IF EXISTS `icon`;
CREATE TABLE `icon` (
  `Id` int NOT NULL,
  `Name` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Acrpnym` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Logo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`Id`) USING BTREE
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of icon
-- ----------------------------
BEGIN;
INSERT INTO `icon` (`Id`, `Name`, `Acrpnym`, `Logo`) VALUES (1, 'XS-Account', 'XS-Assistant the account', NULL);
COMMIT;

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Background` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Image` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `Logo` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `AuthorId` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `ArticleId` varchar(14) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
  `CreateTime` datetime NOT NULL,
  `StateId` int NOT NULL,
  PRIMARY KEY (`Id`) USING BTREE,
  KEY `AuthorId` (`AuthorId`) USING BTREE,
  KEY `article_state_key` (`StateId`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of note
-- ----------------------------
BEGIN;
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (1, 'Background 5', 'Illustration 1', 'Logo 2', '1', '', '2024-04-22 19:58:16', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (2, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000004', '2024-04-22 19:58:18', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (3, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000003', '2024-04-22 19:58:21', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (4, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000002', '2024-04-22 19:58:23', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (5, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000001', '2024-04-22 19:58:25', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (6, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000000', '2024-04-22 19:58:27', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (7, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000006', '2024-04-24 09:50:06', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (8, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000007', '2024-04-24 10:06:06', 1);
INSERT INTO `note` (`Id`, `Background`, `Image`, `Logo`, `AuthorId`, `ArticleId`, `CreateTime`, `StateId`) VALUES (9, 'Background 5', 'Illustration 1', 'Logo 2', 'XS0000000001', 'XSA00000000013', '2024-04-24 18:39:28', 1);
COMMIT;

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `state_id` int NOT NULL,
  `state_name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of state
-- ----------------------------
BEGIN;
INSERT INTO `state` (`state_id`, `state_name`) VALUES (0, '正常');
INSERT INTO `state` (`state_id`, `state_name`) VALUES (1, '异常');
INSERT INTO `state` (`state_id`, `state_name`) VALUES (2, '注销');
INSERT INTO `state` (`state_id`, `state_name`) VALUES (3, '黑名单');
INSERT INTO `state` (`state_id`, `state_name`) VALUES (4, '长时间无活跃');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
