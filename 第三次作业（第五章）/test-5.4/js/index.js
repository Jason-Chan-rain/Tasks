var tr = document.getElementsByName("tr1"); //获取HTML中所有name为tr1的元素
var td = document.getElementsByName("td1"); //获取HTML中所有name为td1的元素

var arr1 = new Array(); //定义二维数组arr2

for (var i = 0; i < tr.length - 1; i++) {
  //因为忽略了表头th，所以少了一组tr，故i<tr.length-1
  arr1[i] = new Array();
  for (var j = 0; j < td.length / (tr.length - 1); j++) {
    //每一组tr中有的td个数：td.length/(tr.length-1)
    arr1[i][j] = ""; //初始化二位数组为空字符串
  }
}

for (var x = 0; x < tr.length - 1; x++) {
  for (var y = 0; y < td.length / (tr.length - 1); y++) {
    arr1[x][y] = td[x * (td.length / (tr.length - 1)) + y].innerText; //td[?].innerText获取第？个td中的值，？= x*(td.length/(tr.length-1))+y
  }
}

function CaculateA() {
  var playA = new Array();
  for (var a = 0; a < tr.length - 1; a++) {
    //   当工资小于26000时
    if (arr1[a][2] < 26000) {
      playA[a] = arr1[a][3] * 100 + arr1[a][2];
      if (playA[a] > 26000) {
        playA[a] = 26000;
      }
    } else {
      playA[a] = arr2[a][2];
    }
  }
}
// 建一个二维数组
// 把工资少于26000的人全部放进二位数组，计算新工资
function CaculateB() {
  var arr2 = []; //新建一个数组
  for (var a = 0; a < tr.length - 1; a++) {
    arr2[a] = [];
    for (var b = 0; b < 3; b++) {
      arr2[a][b] = ""; //初始化二位数组为空字符串
    }
  }
  for (var c = 0; c < tr.length - 1; c++)
    //   当工资小于26000时,把前三栏数据复制到arr2中
    if (arr1[c][2] < 26000) {
      for (var d = 0; d < 3; d++) {
        arr2[c][d] = td[c * (td.length / (tr.length - 1)) + d].innerText; //td[?].innerText获取第？个td中的值，？= x*(td.length/(tr.length-1))+y
      }
      //把新工资存在一个数组里面
      var playB = new Array();
      playA[c] = (arr1[c][2] + (arr1[c][3] * 100) + ((2019-arr1[c][4])*50));//新工资计算公式
    }
}

//点击B按钮计算B方法并把结果显示在页面上
function playB() {
  CaculateB();
  alert(playA[0]);
  //document.getElementById(a + 1).innerHTML = playA[a];
}

function fun() {
  alert(arr2[0][0]);
}
