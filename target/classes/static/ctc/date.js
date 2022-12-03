/**
 * @program: x-admin-plus
 * @description:
 * @author: 陈天赐
 * @create: 2022-12-01 12:56
 * @version:1.0
 **/

//计算日期之间的年份
//startDateStr(2017-02-22) endDateStr(2018-02-21)
function getDateYearSub(startDateStr) {
    var day = 24 * 60 * 60 * 1000;

    var sDate = new Date(Date.parse(startDateStr.replace(/-/g, "/")));
    let eDate = new Date();
    //得到前一天(算头不算尾)
    sDate = new Date(sDate.getTime() - day);

    //获得各自的年、月、日
    var sY = sDate.getFullYear();
    var sM = sDate.getMonth() + 1;
    var sD = sDate.getDate() + 1;
    var eY = eDate.getFullYear();
    var eM = eDate.getMonth() + 1;
    var eD = eDate.getDate();
    if (eY > sY && eM > sM) {
        return eY - sY + 1;
    } else if (eY > sY && eM == sM) {
        if (eD > sD) {
            return eY - sY;
        } else {
            return eY - sY + 1;
        }
    } else {
        return 1;
    }
}
