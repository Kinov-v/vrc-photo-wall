let banner_v = new Vue({
    el:"#myCarousel",
    data:{
        arr:[]
    },
    created:function () {
        axios.get("/banner/select").then(function (response) {
            banner_v.arr = response.data;
        })
    }
})