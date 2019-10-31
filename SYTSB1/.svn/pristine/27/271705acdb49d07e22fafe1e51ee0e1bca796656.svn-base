$('.classification .yj').unbind('click').click(function () {
    $('.classification .yjnr').slideToggle();
    if($('.yj img').attr('src') == 'images/zhankai_03.png'){
        $('.yj img').attr('src','images/zedie_03.png');
    }else{
        $('.yj img').attr('src','images/zhankai_03.png');
    }
});
$('.classification1 .yj1').unbind('click').click(function () {
    $('.classification1 .yjnr1').slideToggle();
    if($('.yj1 img').attr('src') == 'images/zhankai_03.png'){
        $('.yj1 img').attr('src','images/zedie_03.png');
    }else{
        $('.yj1 img').attr('src','images/zhankai_03.png');
    }
});
$('.classification2 .yj2').unbind('click').click(function () {
    $('.classification2 .yjnr2').slideToggle();
    if($('.yj2 img').attr('src') == 'images/zhankai_03.png'){
        $('.yj2 img').attr('src','images/zedie_03.png');
    }else{
        $('.yj2 img').attr('src','images/zhankai_03.png');
    }
});

var oBody = document.getElementById('content');
var oleft = document.getElementsByClassName('left')[0];
var oright = document.getElementsByClassName('right')[0];
var ocenter = document.getElementsByClassName('center')[0];
oBody.style.height = document.documentElement.clientHeight - 54 + 'px';
oleft.style.height = document.documentElement.clientHeight - 54 + 'px';
oright.style.height = document.documentElement.clientHeight - 54 + 'px';
ocenter.style.height = document.documentElement.clientHeight - 54 + 'px';

$('.dianjileft').click(function () {
    $('.left').hide();
    $('.dianjileft').css('left','0px');
    $('.center').css('width','87.5%');
    $('.center').css('left','0');
    if($('.right').css('display') == 'none' && $('.left').css('display') == 'none'){
        $('.center').css('width','100%');
        $('.center').css('left','0');
    }
});
$('.dianjileft').mouseover(function () {
    $('.left').show();
    $('.dianjileft').css('left','229px');
    $('.center').css('width','75%');
    $('.center').css('left','12.5%');
    if($('.right').css('display') == 'none'){
        $('.center').css('width','87.5%');
        $('.center').css('left','12.5%');
    }
});

$('.dianjiright').click(function () {
    $('.right').hide();
    $('.dianjiright').css('right','0px');
    $('.center').css('width','87.5%');
    $('.center').css('left','12.5%');
    if($('.right').css('display') == 'none' && $('.left').css('display') == 'none'){
        $('.center').css('width','100%');
        $('.center').css('left','0');
    }
});
$('.dianjiright').mouseover(function () {
    $('.right').show();
    $('.dianjiright').css('right','229px');
    $('.center').css('width','75%');
    $('.center').css('right','12.5%');
    if($('.left').css('display') == 'none'){
        $('.center').css('width','87.5%');
        $('.center').css('left','0');
    }
});

if($(window).width() == 1600){
    $('.dianjileft').click(function () {
        $('.left').hide();
        $('.dianjileft').css('left','0px');
        $('.center').css('width','87.5%');
        $('.center').css('left','0');
        if($('.right').css('display') == 'none' && $('.left').css('display') == 'none'){
            $('.center').css('width','100%');
            $('.center').css('left','0');
        }
    });
    $('.dianjileft').mouseover(function () {
        $('.left').show();
        $('.dianjileft').css('left','192px');
        $('.center').css('width','75%');
        $('.center').css('left','12.5%');
        if($('.right').css('display') == 'none'){
            $('.center').css('width','87.5%');
            $('.center').css('left','12.5%');
        }
    });

    $('.dianjiright').click(function () {
        $('.right').hide();
        $('.dianjiright').css('right','0px');
        $('.center').css('width','87.5%');
        $('.center').css('left','12.5%');
        if($('.right').css('display') == 'none' && $('.left').css('display') == 'none'){
            $('.center').css('width','100%');
            $('.center').css('left','0');
        }
    });
    $('.dianjiright').mouseover(function () {
        $('.right').show();
        $('.dianjiright').css('right','192px');
        $('.center').css('width','75%');
        $('.center').css('right','12.5%');
        if($('.left').css('display') == 'none'){
            $('.center').css('width','87.5%');
            $('.center').css('left','0');
        }
    });
}

if($(window).width() < 1600){
    $('.dianjileft').click(function () {
        $('.left').hide();
        $('.dianjileft').css('left','0px');
        $('.center').css('width','87.5%');
        $('.center').css('left','0');
        if($('.right').css('display') == 'none' && $('.left').css('display') == 'none'){
            $('.center').css('width','100%');
            $('.center').css('left','0');
        }
    });
    $('.dianjileft').mouseover(function () {
        $('.left').show();
        $('.dianjileft').css('left','166px');
        $('.center').css('width','75%');
        $('.center').css('left','12.5%');
        if($('.right').css('display') == 'none'){
            $('.center').css('width','87.5%');
            $('.center').css('left','12.5%');
        }
    });

    $('.dianjiright').click(function () {
        $('.right').hide();
        $('.dianjiright').css('right','0px');
        $('.center').css('width','87.5%');
        $('.center').css('left','12.5%');
        if($('.right').css('display') == 'none' && $('.left').css('display') == 'none'){
            $('.center').css('width','100%');
            $('.center').css('left','0');
        }
    });
    $('.dianjiright').mouseover(function () {
        $('.right').show();
        $('.dianjiright').css('right','166px');
        $('.center').css('width','75%');
        $('.center').css('right','12.5%');
        if($('.left').css('display') == 'none'){
            $('.center').css('width','87.5%');
            $('.center').css('left','0');
        }
    });
}

/*弹出框*/
$('.shez').click(function (e) {
    $('.tanchu').fadeToggle();
    e.stopPropagation();
});
$('.center').click(function () {
    $('.tanchu').fadeOut();
});
$(".liu").hover(function () {
    $(this).children().show(500);
}, function () {
    $(".mune").hide(500);
})
$('.mune .xi').click(function () {
    $(".mune").hide(500);
});
$('.mune .zong').click(function () {
    $(".mune").hide(500);
});
$('.mune .cu').click(function () {
    $(".mune").hide(500);
});
$('.mune .dacu').click(function () {
    $(".mune").hide(500);
});

/*选中样式*/
$('.yjnr ul li').click(function () {
    var index = $(this).index();
    $('.yjnr ul li').eq(index).addClass('active').siblings().removeClass('active');
});
$('.yjnr1 ul li').click(function () {
    var index = $(this).index();
    $('.yjnr1 ul li').eq(index).addClass('active').siblings().removeClass('active');
});
$('.yjnr2 ul li').click(function () {
    var index = $(this).index();
    $('.yjnr2 ul li').eq(index).addClass('active').siblings().removeClass('active');
});

$('.assemblylist ul li').click(function () {
    var index = $(this).index();
    $('.assemblylist ul li').eq(index).addClass('activeTi').siblings().removeClass('activeTi');
});

$('.assemblyname').click(function () {
    $('.listT').slideToggle(500);
});
$('.listT ul li img').click(function () {
    if($(this).attr('src') == 'images/huiyan_03.png'){
        $(this).attr('src','images/lvyan_03.png')
    }else{
        $(this).attr('src','images/huiyan_03.png')
    }
});