-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: localhost    Database: keeprun
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Product`
--

DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
  `product_id` varchar(45) NOT NULL COMMENT '商品编号',
  `name` varchar(45) DEFAULT NULL COMMENT '商品名称',
  `desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `discount` varchar(45) DEFAULT NULL COMMENT '折扣：小于1，则为打几折；大于等于1，则为减多少钱',
  `price` float DEFAULT NULL COMMENT '商品原始价格',
  `unit` varchar(45) DEFAULT NULL COMMENT '商品单位',
  `detail_url` varchar(255) DEFAULT NULL COMMENT '商品详情链接',
  `category` varchar(45) DEFAULT NULL COMMENT '商品分类',
  `promotion` varchar(255) DEFAULT NULL COMMENT '商品促销',
  `atlas` varchar(255) DEFAULT NULL COMMENT '商品图集',
  `show_loc` int(11) DEFAULT NULL COMMENT '显示在客户端的位置：\n1、首页banner; 2、产地直销； 3、预售产品；\n4、首页推荐1；5；首页推荐2；6、首页推荐3；\n其他；\n',
  `state` varchar(45) DEFAULT NULL COMMENT '商品状态：\n在售，售罄，停售',
  `remain` varchar(45) DEFAULT NULL COMMENT '商品剩余',
  `thumbnail` varchar(255) DEFAULT NULL COMMENT '商品缩略图',
  PRIMARY KEY (`product_id`),
  KEY `product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Product`
--

LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES ('15d737a6-94c0-4869-8caa-89e6ac99b99a','红心猕猴桃','红心猕猴桃具有丰富的营养价值，被誉为“水果之王”、“维C之冠”。在日本称“美容果、聪明果”。珍稀品种---红心猕猴桃红阳猕猴桃皮光无毛，果肉金黄、果心鲜红美丽。口感酸甜清爽、香气浓，具有甜瓜、草莓、柑橘的混合风味和香气，鲜果中有人体必需的17种氨基酸和维C、B、E和钾、钙、镁、磷等矿物质。红心猕猴桃最优秀的特征之一是维C含量十分丰富，为155-420mg/100g，是苹果的20-80倍。每天吃1-2个红心猕猴桃即能满足人体对维C和微量元素的需要。','',12,'','','6','','22dc62fc-0356-4ce4-952e-61320435e8ae.jpg,8e98b971-be18-4c03-ac4b-5dfe1ddf2625.jpg,240f8727-eedd-44a8-86bc-976cfa20d283.jpg,8424f481-0421-4893-9d53-e45ca9a873ad.jpg,8456f287-e6a3-4354-a4d1-08a15b31ae20.jpg,',0,'在售',NULL,'22dc62fc-0356-4ce4-952e-61320435e8ae.jpg'),('1acb5f03-a832-4ed9-ad6c-b2a1e02d41eb','屏山茵红李','果实扁园形，强光面是紫红色，全果着粉，果肉黄绿色，汁多，肉脆，皮薄，离核，于7月中旬成熟。','0.9',8,'','http://www.baidu.com','6','','4c9b97b2-0dcd-4802-a5e9-5415652acf8a.jpg,17f93d2e-3dc1-44f1-aacf-282731b12e9d.jpg,0d832377-1d6e-4047-8595-0179f916806e.jpg,48907ce1-280e-4305-b779-49306e1a6d77.jpg,',2,'在售',NULL,'4c9b97b2-0dcd-4802-a5e9-5415652acf8a.jpg'),('24063e4c-40fb-4b7f-8b74-261e301e321d','龙眼','龙眼含丰富的葡萄糖、蔗糖和蛋白质等，含铁量也比较高，可提高热能、补充营养。','',16,'','','6','','23abfd50-6883-43d0-8fbc-8a0549f17188.jpg,17f93d2e-3dc1-44f1-aacf-282731b12e9d.jpg,0d832377-1d6e-4047-8595-0179f916806e.jpg,48907ce1-280e-4305-b779-49306e1a6d77.jpg,',4,'在售',NULL,'23abfd50-6883-43d0-8fbc-8a0549f17188.jpg'),('2c3e7b72-0dbf-46a0-8f95-2f452130b2b5','新疆哈密瓜','哈密瓜有“瓜中之王”的美称，含糖量高，形态各异，风味独特，有的带奶油味，有的含柠檬香，但都味甘如蜜，奇香袭人，饮誉国内外。在诸多哈密瓜品种中，以“红心脆”、“黄金龙”品质最佳。哈密瓜不仅好吃，而且营养丰富，药用价值高。','',15,'','','','','c8786f14-2f5b-4ed2-b39b-3cc5b0bc793a.jpg,1797833a-bb00-4289-a293-8af96c86ed91.jpg,4e87d6a7-9967-406a-8c89-4ec09c8785bd.jpg,35a85314-9389-4e77-be92-83e283bc029b.jpg,44e1fa9a-c1ba-45a3-a3cc-3ec1fb8e80ac.jpg,',6,'在售',NULL,'c8786f14-2f5b-4ed2-b39b-3cc5b0bc793a.jpg'),('2cf1a6ef-d1ed-4e43-9989-816284e19d0a','火龙果','火龙果的果肉几乎不含果糖和蔗糖，糖分以葡萄糖为主，这种天然葡萄糖，容易吸收，适合运动后食用。','',8,'','','','','09e70fff-ffb8-406e-a5c5-a33d28f0defd.jpg,c01c2bf5-233e-413f-a152-be930a255b60.jpg,fc9e07c6-b9f4-4994-88e9-866074695d0e.jpg,d6686b6a-2c81-4137-89d0-ff6e83bed094.jpg,519eab9d-44bf-4588-b0bb-89c529049817.jpg,',6,'在售',NULL,'09e70fff-ffb8-406e-a5c5-a33d28f0defd.jpg'),('2dea41ec-b1d5-4c4b-9298-793e3a75d9e7','荔枝','荔枝营养丰富，含葡萄糖、蔗糖、蛋白质、脂肪以及维生素A、B、C等，并含叶酸、精氨酸、色氨酸等各种营养素，对人体健康十分有益。荔枝具有健脾生津，理气止痛之功效，适用于身体虚弱，病后津液不足，胃寒疼痛，疝气疼痛等症。现代研究发现，荔枝有营养脑细胞的作用，可改善失眠、健忘、多梦等症，并能促进皮肤新陈代谢，延缓衰老。然而，过量食用荔枝或某些特殊体质的人食用荔枝，均可能发生意外。','',16,'','','6','','6ca250f0-a195-4c23-9f22-f285f164b9c1.jpg,6482401d-40fe-4279-898e-02012d496243.jpg,917cd3b6-f839-4cf2-8fab-b6bd3b564789.jpg,85f84170-391e-4cb6-a726-bbc706e0648d.jpg,f10249ac-fbbd-468e-861f-5107478e6873.jpg,',4,'在售',NULL,'6ca250f0-a195-4c23-9f22-f285f164b9c1.jpg'),('3a72afbd-3ca4-4968-a0d6-7b92ab18c958','麒麟瓜','麒麟瓜是西瓜的一种。麒麟瓜是一个经过培植的科研新品种，对生长环境和土壤以及日照的要求非常高','',5,'','','','','01008ce6-4fd2-4497-bc60-fe311b35018f.jpg,70b31e74-28bf-4db3-8330-975674341d94.jpg,007aa54d-d986-485b-aec2-6cf926ee2106.jpg,a906b3f8-3ae2-49d0-89e1-d6e31c420e40.jpg,e93bdf8d-53bf-4a99-bd5e-4596ea33f5ab.jpg,',2,'在售',NULL,'01008ce6-4fd2-4497-bc60-fe311b35018f.jpg'),('88d879b1-541a-4ebf-ba7c-87171a85d0f8','榴莲','榴莲性热，可以活血散寒，缓解痛经，特别适合受痛经困扰的女性食用；它还能改善腹部寒凉的症状，可以促使体温上升，是寒性体质者的理想补品。榴莲营养价值极高，经常食用可以强身健体，健脾补气，补肾壮阳，暖和身体。','',20,'','','6','','48006a9e-cabc-4ec9-86ba-3374106379aa.jpg,270f1244-7301-42e5-8428-758051515920.jpg,989dd8f3-a517-4e5f-b363-eda1703e2181.jpg,4258bcc4-c346-4103-9c29-5ac5a7bfabcb.jpg,1cd52193-d19a-4c9f-8920-cad585e884dc.jpg,',1,'在售',NULL,'48006a9e-cabc-4ec9-86ba-3374106379aa.jpg'),('8d3765e5-d4f1-474c-8184-71b20e1da0fd','枇杷','成熟的枇杷味道甜美，营养颇丰，有各种果糖、葡萄糖、钾、磷、铁、钙以及维生素A、B、C等。当中胡萝卜素含量在各水果中为第三位。中医认为枇杷果实有润肺、止咳、止渴的功效。吃枇杷时要剥皮。除了鲜吃外，亦有以枇杷肉制成糖水罐头，或以枇杷酿酒。枇杷不论是叶、果和核都含有扁桃苷。','',18,'','','6','','c11472d9-c8f6-463c-a950-51bdfd9d7460.jpg,15455fbc-d1ee-4eea-981f-0cc733712bd0.jpg,4feb1ac4-98c1-4338-a0ef-754a48dc9925.jpg,f3d95864-7cbc-4c5e-8191-84cc866e45e2.jpg,09069475-ec67-4782-993f-f5bf490a1a66.jpg,',4,'在售',NULL,'c11472d9-c8f6-463c-a950-51bdfd9d7460.jpg'),('a35f7517-e5cd-45ab-8901-982968764c91','蜜柚','心脑血管病及肾脏病患者常利用药物来排除体内多余的钠。柚中正好含有这些患者必须的天然矿物质——钾，却几乎不含钠，是最佳的食疗水果。柚中含有大量的维生素C，能降低血液中的胆固醇，是现代人追求健康的理想食物。','',8,'','','','','8fbaca94-1b14-4677-b9c4-3cfb0bb9c760.jpg,db43defd-039d-4cc4-9783-592e08951f00.jpg,49a7bd48-bda0-44e1-818a-37c4bc50e2fb.jpg,8eec9368-dbe9-475f-beee-792f6318475a.jpg,d6dfb783-dfc3-4c08-8c7d-0e9c0fff3e67.jpg,',0,'在售',NULL,'8fbaca94-1b14-4677-b9c4-3cfb0bb9c760.jpg'),('a742ea55-83cf-4989-98f8-e5bd54ac6fb6','菠萝','凤梨营养丰富，其成分包括糖类、蛋白质、脂肪、维生素A、B1.B2.C、蛋白质分解酵素及钙、磷、铁、有机酸类、尼克酸等，尤其以维生素C含量最高。','',3,'','','6','','fb3abb6d-67b4-4e6a-9260-d65d82daeafe.jpg,ded57c81-ca07-4bf9-baa5-6eedf3d0109a.jpg,5355850c-3722-458a-9783-c2527690122b.jpg,352018ef-7549-4e20-ac80-bbf6e458a1c3.jpg,f22ebf7b-1fb0-4482-9805-5a4e4494e384.jpg,',4,'在售',NULL,'fb3abb6d-67b4-4e6a-9260-d65d82daeafe.jpg'),('af704b76-b405-4269-a2d8-dd877d9209bf','糖心苹果','因阿克苏高海拔区域昼夜温差大、光照充足、土壤肥沃，使苹果含糖量高，糖度在18度左右口味特别甜； 阿克苏苹果都采用冰川雪水浇灌、沙性土壤栽培等特性，使苹果的果核部分糖分堆积成透明状，形成了世界上独一无二的“糖心”红富士苹果。','',8,'','','6','','7a1f0581-e12d-4b88-a007-3acdd8e8573f.jpg,a07c958f-05e2-41e9-b70b-d02b94790f8f.jpg,63f11ed9-a63d-4420-914c-79ba8280f23d.jpg,ae737350-cba2-4c56-a4e9-7d5c5e4c752c.jpg,194c7916-69be-4762-a2c2-bce788880dd9.jpg,',4,'在售',NULL,'7a1f0581-e12d-4b88-a007-3acdd8e8573f.jpg'),('b4c761e1-a3fd-4d35-9b7d-c00c4ffddbb0','巧克力草莓','草莓中富含丰富的胡萝卜素与维生素A，可缓解夜盲症，具有维护上皮组织健康、明目养肝，促进生长发育之效。草莓中富含丰富的膳食纤维，可促进胃肠道的蠕动，促进胃肠道内的食物消化，改善便秘，预防痤疮、肠癌的发生。','',8,'','','','','e0f2230a-fdbb-4c6f-953d-ed1e08c0485c.jpg,5def7b5d-578a-4b8e-8f11-60bbd0e22151.jpg,3a9a9832-8056-4ec3-a2fd-f5dc4a669f68.jpg,54881872-cdcb-420b-94dd-d78a7886fc29.jpg,9d1f88fd-1fbd-4f55-afd8-91c549752672.jpg,',0,'在售',NULL,'e0f2230a-fdbb-4c6f-953d-ed1e08c0485c.jpg'),('bcec3c6e-a43d-477f-b229-2a8a81c6a668','椪柑','椪柑又名芦柑，皮薄易剥，色泽鲜美，果肉橙红色，汁多、组织紧密、浓甜脆嫩，化渣爽口、籽少，且有药用功效。适合南方各省生长。','',5,'','','6','','bf15454e-bdc1-4dea-a1a8-6c4f5cd3fc48.jpg,43a7e1b8-eba8-43b4-b7c0-97a28d146e7e.jpg,37d1249a-2889-4493-8818-808f9bdc31ff.jpg,582cc21e-ecb3-4b03-92f1-ad567b900519.jpg,e44d1d93-0a73-4624-a54b-57a3fef3dc28.jpg,',1,'在售',NULL,'bf15454e-bdc1-4dea-a1a8-6c4f5cd3fc48.jpg'),('cff9d840-6d49-4895-904c-16fa45fbdb8d','天然椰果','椰汁及椰肉含大量蛋白质、果糖、葡萄糖、蔗糖、脂肪、维生素B1、维生素E、维生素C、钾、钙、镁等。椰肉色白如玉，芳香滑脆；椰汁清凉甘甜。椰肉、椰汁是老少皆宜的美味佳果。','',6,'','','','','2783234d-34fd-4e93-97fd-2f4133380437.jpg,611be873-aeb5-4a8a-8ab4-9b8dda7c8aec.jpg,d690cdf5-9e1c-4d50-9b2d-d8ea2b5e64cd.jpg,84229a9c-3cfb-48f4-940c-ea221749a23c.jpg,abb65747-b37c-461e-b25b-58e91fd7b407.jpg,',6,'在售',NULL,'2783234d-34fd-4e93-97fd-2f4133380437.jpg'),('dd7d131c-33e1-4a47-bede-5fe5f0c781a4','水蜜桃','水蜜桃有美肤、清胃、润肺、祛痰等功效。它的蛋白质含量比苹果、葡萄高一倍，比梨子高七倍，铁的含量比苹果多三倍，比梨子多五倍，富含多种维生素，其中维生素C最高。','',15,'','','6','','3f60231c-1d42-4b90-ba75-ce6b128b62bf.jpg,313f795e-02ed-4a45-bd0e-d68f1ef24fe8.jpg,3d81dba5-13be-45c9-993d-ef26324db15e.jpg,67b53230-876a-40c3-b822-4b5c87d9ecaa.jpg,823f0487-2050-4406-bae1-bc6968b11641.jpg,',3,'在售',NULL,'3f60231c-1d42-4b90-ba75-ce6b128b62bf.jpg'),('e75002ec-63fe-424c-9624-1af93046d753','珍珠樱桃','樱桃可以缓解贫血。铁是合成人体血红蛋白的原料，对于女性来说，有着极为重要的意义。世界卫生组织的调查表明，大约有50%的女童、20%的成年女性、40%的孕妇会发生缺铁性贫血。','',22,'','','','','a99c9499-1c49-480b-a10f-3b206f688846.jpg,54b1eb21-ed7a-45e1-96a3-a306335fe85a.jpg,4c9b97b2-0dcd-4802-a5e9-5415652acf8a.jpg,7008de2f-8504-4161-a1ea-9bc5b06cfb6d.jpg,19af22ae-1475-4dda-9076-0b335ca0256f.jpg,',1,'在售',NULL,'a99c9499-1c49-480b-a10f-3b206f688846.jpg');
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-26 15:01:37
