$(function(){

   setTimeout(function(){
     $('.slider li .text0').addClass('on');
    $('.slider li .atext0').addClass('on');
   },1000);
   //1초후 최초 한번만 실행

var bx = $('.slider').bxSlider({
    auto:true,
    controls:false,
    pager:false,
    mode:'fade',
    pause:5000,
    autoHover:true,
    onSlideAfter:function(){
        var k = bx.getCurrentSlide(); //현재의 슬라이드번호
        $('.slider li').find('h2').removeClass('on');
        $('.slider li').find('p').removeClass('on');
        $('.slider li .text' + k).addClass('on');
        $('.slider li .atext' + k).addClass('on');
    }
});



});