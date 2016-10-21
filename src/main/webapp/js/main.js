(function ($) {

    "use strict";

    $(".main-menu a").click(function () {
        var id = $(this).attr('class');
        id = id.split('-');
        $('a.active').removeClass('active');
        $(this).addClass('active');
        $("#menu-container .content").slideUp('slow');
        $("#menu-container #menu-" + id[1]).slideDown('slow');
        $("#menu-container .homepage").slideUp('slow');
        return false;
    });


    $(".main-menu a.homebutton").click(function () {
        $("#menu-container .content").slideUp('slow');
        $("#menu-container .homepage").slideDown('slow');
        $(".logo-top-margin").animate({marginLeft: '45%'}, "slow");
        $(".logo-top-margin").animate({marginTop: '120px'}, "slow");
        return false;
    });

    $(".main-menu a.aboutbutton").click(function () {
        $("#menu-container .content").slideUp('slow');
        $("#menu-container .about-section").slideDown('slow');
        $(".logo-top-margin").animate({marginTop: '0'}, "slow");
        $(".logo-top-margin").animate({marginLeft: '0'}, "slow");
        return false;
    });

    $(".main-menu a.projectbutton").click(function () {
        $("#menu-container .content").slideUp('slow');
        $("#menu-container .gallery-section").slideDown('slow');
        $(".logo-top-margin").animate({marginTop: '0'}, "slow");
        $(".logo-top-margin").animate({marginLeft: '0'}, "slow");
        return false;
    });

    $(".main-menu a.contactbutton").click(function () {
        $("#menu-container .content").fadeOut();
        $("#menu-container .contact-section").slideDown('slow');
        $(".logo-top-margin").animate({marginTop: '0'}, "slow");
        $(".logo-top-margin").animate({marginLeft: '0'}, "slow");
        return false;
    });

    $('.toggle-menu').click(function () {
        $('.show-menu').stop(true, true).slideToggle();
        return false;
    });

    $('.show-menu a').click(function () {
        $('.show-menu').fadeOut('slow');
    });
    //新增
    $('#button').click(function () {
        alert(1);
        //contact-form
        var formParam = $("#contact-form").serialize();//序列化表格内容为字符串
        $.ajax({
            type: 'post',
            url: 'blog/test.do',
            data: formParam,
            cache: false,
            // dataType:'json',
            success: function () {
                alert(2);
            },
            error: function () {
                alert(3);
            }
        });

    });

})(jQuery);

