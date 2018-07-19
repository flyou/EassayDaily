- [每日一文](#article)
- [特定某天一文](#someday)
- [随机一文](#random)

<h2 id="article">每日一文</h2>

url：[`https://interface.meiriyiwen.com/article/today?dev=1`](https://interface.meiriyiwen.com/article/today?dev=1)

json 示例：

	{
      "data": {
        "date": {
          "curr": "20170217",
          "prev": "20170216",
          "next": "20170218"
        },
        "author": "契诃夫",
        "title": "散戏之后",
        "digest": "12",
        "content": "",
        "wc": 1963
      }
    }

解析：

- `date`：日期
	- `curr`：今日日期，`yyyyMMdd` 格式
	- `prev`：昨日日期，`yyyyMMdd` 格式
	- `next`：明日日期，`yyyyMMdd` 格式
- `author`：作者
- `titile`：标题
- `digest`：首段
- `content`：正文内容
- `wc`：字数(word count)

<h2 id="someday">特定某天一文</h2>

url：https://interface.meiriyiwen.com/article/day?dev=1&date= + 日期

url 示例：[`https://interface.meiriyiwen.com/article/day?dev=1&date=20170216`](https://interface.meiriyiwen.com/article/day?dev=1&date=20170216)

json 示例以及解析同[每日一文](#article)

<h2 id="random">随机一文</h2>

url：[https://interface.meiriyiwen.com/article/random?dev=1](https://interface.meiriyiwen.com/article/random?dev=1)

json 示例以及解析同[每日一文](#article)
