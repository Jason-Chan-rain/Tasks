var compeleteCards = new Array();
var mytimer;
var cardSequence = 1;
var player1 = new Array();
//储存玩家一的手牌
var player2 = new Array();
//储存玩家二的手牌

function Cards(number, type) {
  var card = {
    number: number,
    type: type
  };
  return card;
}

function CreatCompeleteCard() {
  var arr = new Array();
  for (var i = 3; i <= 15; i++) {
    for (var j = 1; j <= 4; j++) {
      var card = Cards(i, j);
      arr.push(card);//push方法可向数组末尾添加新元素并返回新长度
    }
  }
  return arr;
}

// 展示花色
function Show() {
  function typeShow(type) {
    var t;
    switch (type) {
      case 1:
        t = "♠";
        break;
      case 2:
        t = "♣";
        break;
      case 3:
        t = "<font color='#FF0000'>♦</font>";
        break;
      case 4:
        t = "<font color='#FF0000'>♥</font>";
        break;
    }
    return t;
  }

  // 展示牌上对应值
  function numberShow(number) {
    var n = number;
    switch (number) {
      case 11:
        n = "J";
        break;
      case 12:
        n = "Q";
        break;
      case 13:
        n = "K";
        break;
      case 14:
        n = "A";
        break;
      case 15:
        n = "2";
        break;
    }
    return n;
  }

  function arrayToShow(array, id) {
    var html = "";
    for (var i = 0; i < array.length; i++) {
      html +=
        "<div class='card'><b>" +
        typeShow(array[i].type) +
        "</b><div class='number'>" +
        numberShow(array[i].number) +
        "</div></div>";
    }
    document.getElementById(id).innerHTML = html;
  }
  arrayToShow(compeleteCards, "compeleteCards");
  arrayToShow(player1, "player1");
  arrayToShow(player2, "player2");
}

//洗牌
function SortCards() {
  if (compeleteCards.length == 52) {
    compeleteCards.sort(function(a, b) {
      return 0.5 - Math.random();
    });
  }
}
//发牌
function DealCards() {
  if (compeleteCards.length == 52) {
    mytimer = setInterval("GetCards(compeleteCards.shift())", 100);
  }
}

//玩家接受扑克牌CardObj插入自身的数组
function GetCards(CardObj) {
  switch (cardSequence) {
    case 1:
      var k = InCardsIndex(player1, CardObj);
      player1.splice(k, 0, CardObj);
      cardSequence = 2;
      break;
    case 2:
      var k = InCardsIndex(player2, CardObj);
      player2.splice(k, 0, CardObj);
      cardSequence = 1;
      break;
    
  }

  if (compeleteCards.length == 0) {
    window.clearTimeout(mytimer);
  }
  Show();
}

//在此添加代码
function InCardsIndex(arr, obj) {
  var len = arr.length;
  if (len == 0) {
    return 0;
  } else if (len == 1) {
    if (obj.number >= arr[0].number) {
      return 1;
    } else {
      return 0;
    }
  } else {
    var backi = -1;
    for (var i = 0; i < len; i++) {
      if (obj.number <= arr[i].number) {
        backi = i;
        break;
      }
    }
    if (backi == -1) {
      backi = len;
    }
    return backi;
  }
}
document.getElementById("create").onclick = function() {
  compeleteCards = CreatCompeleteCard();
  Show();
};
document.getElementById("sort").onclick = function() {
  SortCards();
  Show();
};
document.getElementById("deal").onclick = function() {
  DealCards();
};
