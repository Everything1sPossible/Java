 window.onload=function(){
        setInterval(function(){
            var str,colorhead,colorfoot;
            var objD = new Date();       //获取当前时间
            var yy = objD.getYear();     //年
            if(yy<1900) yy = yy+1900;
            var MM = objD.getMonth()+1;  //月
            if(MM<10) MM = '0' + MM;
            var dd = objD.getDate();     //日
            if(dd<10) dd = '0' + dd;
            var hh = objD.getHours();    //时
            if(hh<10) hh = '0' + hh;
            var mm = objD.getMinutes();  //分
            if(mm<10) mm = '0' + mm;
            var ss = objD.getSeconds();  //秒
            if(ss<10) ss = '0' + ss;
            var ww = objD.getDay();      //星期数
            if ( ww==0 ) colorhead="<font color=\"#FF0000\">";
            if ( ww > 0 && ww < 6 ) colorhead="<font color=\"#373737\">";
            if ( ww==6 ) colorhead="<font color=\"#008000\">";
            if (ww==0) ww="星期日";
            if (ww==1) ww="星期一";
            if (ww==2) ww="星期二";
            if (ww==3) ww="星期三";
            if (ww==4) ww="星期四";
            if (ww==5) ww="星期五";
            if (ww==6) ww="星期六";
            colorfoot="</font>";
            //进行自定义拼接
            str = colorhead + yy + "年" + MM + "月" + dd + "日" + hh + ":" + mm + ":" + ss + " " + ww + colorfoot;
            document.getElementById("showTime").innerHTML=str;
        },1000); //每隔一秒执行一次
     };