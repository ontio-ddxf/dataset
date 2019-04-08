<!-- vscode-markdown-toc -->
* 1. [Setup](#Setup)
	* 1.1. [启动 Elastic Search](#ElasticSearch)
	* 1.2. [启动本项目，使用swagger查看接口](#swagger)
* 2. [Restful Api 需求](#RestfulApi)
* 3. [Restful接口规范](#Restful)
	* 3.1. [插入数据到ElasticSearch](#ElasticSearch-1)
	* 3.2. [查询数据，分页返回](#)
	* 3.3. [根据ID返回数据](#ID)
	* 3.4. [权限](#-1)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# dataset

dataset是一个数据集合，数据提供方可以插入数据到搜索引擎，数据需求方可以查询数据。


##  1. <a name='Setup'></a>Setup



###  1.1. <a name='ElasticSearch'></a>启动 Elastic Search

进入docker-elk在命令行输入docker-compose up

###  1.2. <a name='swagger'></a>启动本项目，使用swagger查看接口


##  2. <a name='RestfulApi'></a>Restful Api 需求

暴露给用户的是 `Restful Api`。
有以下场景：

1. 数据提供方插入数据或根据id更新数据。
2. 根据Tag查询并分页展示。
3. 根据ID查询数据详细信息。

##  3. <a name='Restful'></a>Restful接口规范

###  3.1. <a name='ElasticSearch-1'></a>插入或根据id更新数据到ElasticSearch

```
url：/api/v1/dataset
method：POST
```

请求：

```source-json
{
	"id":"dfyyjTTHdfrddsfikjYUkh",
	"data":["青少年疾病","骨科","骷髅腿"],
	"price":"1",
	"coin":"ong"
}
```

| Field Name | Type | Description |
|---|---|---|
|id|String|标识一条数据，查询系统生成|
|data|List|数据属性|
|Price|String|价格|
|coin|String|货币种类|

响应：

```source-json
{
  "error":0,
  "desc":"SUCCESS",
  "result": "ID"
}
```
| Field Name | Type | Description |
| :-- | :-- | :-- |
| error | int | 错误码 |
| desc | String | 成功为SUCCESS，失败为错误描述 |
| result | String | 成功返回数据ID，失败返回"" |


###  3.2. <a name=''></a>查询数据，分页返回

```
url：/api/v1/dataset?{page_index=}&{page_offset=}
method：POST
```
根据存储数据的属性以及匹配百分比查询并返回分页数据。

请求：

```source-json
[
  {
    "percent": 100,
    "text": "上海"
  },
  {
      "percent": 100,
      "text": "青少年"
    }
]
```

| Field Name | Type | Description |
|---|---|---|
|percent|Integer|匹配度|
|text|String|数据属性|

响应：

```source-json
{
  "error": 0,
  "desc": "SUCCESS",
  "result": {
    "currentPage": 0,
    "pageSize": 2,
    "recordCount": 2,
    "recordList": [
      {
              "tag0": "上海第九人民医院",
              "tag1": "青少年疾病",
              "createTime": "2019-04-01 11:58:09",
              "price": "1",
              "id": "1",
              "tag2": "骨科",
              "coin": "ont"
            }
          ],
          "pageCount": 1,
          "beginPageIndex": 1,
          "endPageIndex": 1
        }
      }
```

| Field Name | Type | Description |
| :-- | :-- | :-- |
| error | int | 错误码 |
| desc | String | 成功为SUCCESS，失败为错误描述 |
| result | Object | 返回分页数据 |
| total | String |总页数|
|records|Array|Array里面每个数据和插入的数据一个格式|

###  3.3. <a name='ID'></a>根据ID返回数据

```
url:/api/v1/dataset/id
method:Get
```

响应：

```source-json
{
  "error": 0,
  "desc": "SUCCESS",
  "result": {
    "tag0": "上海第九人民医院",
    "tag1": "青少年疾病",
    "createTime": "2019-04-01 11:58:09",
    "price": "1",
    "id": "1",
    "tag2": "骨科",
    "coin": "ont"
  }
}
```

| Field Name | Type | Description |
| :-- | :-- | :-- |
| error | int | 错误码 |
| desc | String | 成功为SUCCESS，失败为错误描述 |
| result | Object | 返回数据 |

###  3.4. <a name='-1'></a>权限

目前Restful API没有设计权限系统，由使用代码的第三方自己实现
