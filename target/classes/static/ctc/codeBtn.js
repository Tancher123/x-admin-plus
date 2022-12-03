/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-12-02 20:37
 * @version:1.0
 **/

// maxTime:倒计时时间  sendCode：要操作的按钮
function codeBtn(maxTime,sendCode) {
    function jia(a) {
        if (a < 10)
            return "0" + a;
        else
            return a;
    }

    var last = function () {
        maxTime = jia(maxTime);
        var mes = maxTime + " 秒后重发";
        sendCode.innerHTML = '<b style="color: #ea5800">' + mes + '</b>';
        maxTime--;
        if (maxTime <= 0) {
            clearInterval(interval);//关闭定时器
            // clearInterval(timeId);
            sendCode.innerHTML = '重发验证码';
            // $("#sendCode").removeAttr("disabled", "disabled").removeClass("layui-btn-disabled");
            sendCode.removeAttr("disabled","disabled").removeClass("layui-btn-disabled");
        }
    };
    var interval = setInterval(function () {
        last();
    }, 1000);
}
