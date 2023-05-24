let product_v = new Vue({
    el: ".grid",
    data: {
        arr: []
    },
    created: function () {
        let url = "/product/selectForIndex";
        if (location.search.indexOf("wd") != -1) {
            let wd = location.search.split("=")[1];
            url = "/product/selectByWd?wd=" + wd;
        } else
            //得到地址栏中的参数并判断是否包含cid
        if (location.search.indexOf("cid") != -1) {
            let cid = location.search.split("=")[1];
            url = "/product/selectByCid?cid=" + cid;
        }
        //发请求获取数据
        axios.get(url).then(function (response) {
            product_v.arr = response.data;
        })

    },
    updated: function () { //每次当data里面的变量重新赋值后 自动调用此方法
        //初始化瀑布流
        $(".grid").imagesLoaded().progress(function () {
            $(".grid").masonry({
                itemSelector: ".grid-item",
                columnWidth: 210
            })
        })
        //给瀑布流元素添加移入移出事件
        $(".grid-item").hover(function () {//鼠标移入执行
            //$(this)代表移入的div  .children("div")得到里面的蓝条div
            $(this).children("div").fadeIn(250);
        }, function () {//鼠标移出执行
            $(this).children("div").fadeOut(250);
        })
    }
})