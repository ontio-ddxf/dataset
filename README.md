<!-- vscode-markdown-toc -->
* 1. [Restful Api 需求](#RestfulApi)
* 2. [Restful接口规范](#Restful)
	* 2.1. [插入数据到ElasticSearch](#ElasticSearch)
	* 2.2. [更新数据到ElasticSearch](#ElasticSearch-1)
	* 2.3. [查询数据，分页返回](#)
	* 2.4. [根据ID返回数据](#ID)
	* 2.5. [权限](#-1)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

# dataset

dataset是一个数据集合，数据提供方可以插入数据到搜索引擎，数据需求方可以查询数据。



##  1. <a name='RestfulApi'></a>Restful Api 需求

暴露给用户的是 `Restful Api`。
有以下场景：

1. 数据提供方插入数据。
2. 数据提供方更新价格和其他信息。
3. 根据Tag查询并分页展示。
4. 根据ID查询数据详细信息。

##  2. <a name='Restful'></a>Restful接口规范

###  2.1. <a name='ElasticSearch'></a>插入数据到ElasticSearch

```
url：/api/v1/dataset
method：POST
```

请求：

```source-json
{
	"Name":"上海第九人民医院青少年骷髅腿数据",
	"TAG1":"青少年疾病",
        "TAG2":"骨科",
        "TAG3":"上海第九人民医院青少年骷髅腿数据1949年到2004年",
        "StartTimeCollected":"20110404",
        "EndTimeCollected":"20120909",
        "Price":"0.1",
        "CoinType":"ong"
}
```

| Field Name | Type | Description |
|---|---|---|
|Name|String|标识一条数据，用户插入数据的时候指定|
|ID|String|标识一条数据，查询系统生成|
|Tag1|String|大类别|
|Tag2|String|小类别|
|Tag3|Text|描述，属性|
|Price|String|价格|
|CoinType|String|货币种类|
|StartTimeCollected|Date|UTC时间，数据开始采集的时间|
|EndTimeCollected|Date|UTC时间，数据结束采集的时间|

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

###  2.2. <a name='ElasticSearch-1'></a>更新数据到ElasticSearch

```
url：/api/v1/dataset
method：PUT
```

请求：

```source-json
{
        "ID":"0000000001",
	"Name":"上海第九人民医院青少年骷髅腿数据",
	"TAG1":"青少年疾病",
        "TAG2":"骨科",
        "TAG3":"上海第九人民医院青少年骷髅腿数据1949年到2004年",
        "StartTimeCollected":"20110404",
        "EndTimeCollected":"20120909",
        "Price":"0.1",
        "CoinType":"ong"
}
```
数据格式和插入的一样。

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

###  2.3. <a name=''></a>查询数据，分页返回

```
url：/api/v1/dataset?{name=}&{tag1=}&{tag2=}&{tag3=}&&{page_index=}&{page_offset=}
method：Get
```
返回分页数据。tag1，tag2，name，id都是精确匹配，tag3是全文检索。

响应：

```source-json
{
  "error":0,
  "desc":"SUCCESS",
  "result": {
            "total": "",
            "records": []
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

###  2.4. <a name='ID'></a>根据ID返回数据

```json
url：/api/v1/dataset/id
method：Get
```

响应：

```source-json
{
  "error":0,
  "desc":"SUCCESS",
  "result": {
        //和插入的数据一样
    }
}
```

###  2.5. <a name='-1'></a>权限

目前Restful API没有设计权限系统，由使用代码的第三方自己实现
