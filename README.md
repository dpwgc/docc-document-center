# DOCC-Document-Center
## 基于Elasticsearch的文档检索中心（文档中台系统）

* 在写

***

### Elasticsearch索引
#### 索引名称：docc-document-center-product
* id `ES主键id` `keyword`
* app_id `文档所属应用id` `keyword`
* category_id `文档所属分类id` `keyword`
* author_id `文档作者id` `keyword`
* document_id `文档id` `keyword`
* title `文档标题` `keyword`
* content `文档内容` `keyword`
* tags `文档标签` `keyword`
* summary `文档总结摘要` `keyword`
* auth_level `文档查看权限级别（0～99自定义，用户权限等级如果低于authLevel，则不能查看该文章）` `Long`
* score `文档推荐分值（自定义，可按此字段对文档进行排序，用于热门文档推荐）` `Long`
* love `文档收藏数（可按此字段对文档进行排序）` `Long`
* like `文档点赞数（可按此字段对文档进行排序）` `Long`
* read `文档阅读数（可按此字段对文档进行排序）` `Long`
* type `文档类型（自定义，例：0-普通文档，1-置顶文档）` `Long`
* status `文档状态（0-删除，1-正常）` `Long`
* create_time `文档创建时间` `Long`
* update_time `文档更新时间` `Long`

