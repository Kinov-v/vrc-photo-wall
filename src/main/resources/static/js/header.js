Vue.component('myheader', {
    template: `
    <header class="container">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">
                        <img alt="Brand" src="images/logo.png">
                    </a>
                </div>
                <!--分类开始-->
                <ul class="nav navbar-nav">
                    <li><a href="/">首页</a></li>
                    <li v-for="c in arr"><a :href="'/index.html?cid='+c.id">{{c.name}}</a></li>
                    <li>
                        <form action="">
                            <input type="text" v-model="wd" placeholder="Search">
                            <button type="button" @click="search()">
                                <i class="fa fa-search"></i>
                            </button>
                        </form>
                    </li>
                </ul>
                <!--分类结束-->
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="javascript:void(0)" @click="admin()">管理员入口</a></li>
                </ul>
            </div>
        </nav>
    </header>
    `,
    data: function () {
        return {
            arr: [],
            wd: ""
        }
    },
    created: function () {
        let v = this;
        //因为此处发出异步请求,需要涉及子线程,在方法内部的this代表的不是组件实例
        //需要通过单独变量记录来实现
        axios.get("/category/select").then(function (response) {
            v.arr = response.data;
        })
    },
    methods: {
        admin: function () {
            //发请求询问是否登录过
            axios.get("/currentUser").then(function (response) {
                if (response.data == "") {
                    location.href = "/login.html";
                } else {
                    location.href = "/admin.html";
                }
            })
        },
        search: function () {
            //再次跳转到首页   this代表当前组件实例
            location.href = "/index.html?wd=" + this.wd;
        }
    }
})
let header_v = new Vue({
    el: "myheader",
})