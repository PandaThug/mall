function stringtoObj(str){
    var str2=str.substring(1,str.length-1);
    let obj = {};
    let arr1 = str2.split(', ');
    arr1.forEach(function(item){
       //每一项元素，再根据=号字符 打散成数组
       let kv = item.split('=')
          //每次向对象里面添加 属性和属性值
          obj[kv[0]] = kv[1]
      })
      return obj;
}
//http没有引号的情况，用于商品详情页
function stringtoObjWashed2(str){
  var str2=str.substring(1,str.length-1);
  let obj = {};
  let arr1 = str2.split(', ');
  arr1.forEach(function(item){
     //每一项元素，再根据=号字符 打散成数组
     let kv = item.split('=');
        //每次向对象里面添加 属性和属性值
        //若值有引号则去掉
        if(kv.length==2){
          if(kv[1][kv[1].length-1]=='}')
        kv[1]=kv[1].substring(0,kv[1].length-1);
        if(kv[1][0]=='\'')
          kv[1]=kv[1].substring(1,kv[1].length-1);
         
        obj[kv[0]] = kv[1]
        }
        else{
          var http=item.substring(12,item.length);
          obj["goodPicture"]=http;
        }
        
    })
    return obj;
}
//http有引号的情况……
function stringtoObjWashed(str){
    var str2=str.substring(1,str.length-1);
    let obj = {};
    let arr1 = str2.split(', ');
    arr1.forEach(function(item){
       //每一项元素，再根据=号字符 打散成数组
       let kv = item.split('=');
          //每次向对象里面添加 属性和属性值
          //若值有引号则去掉
          if(kv.length==2){
            if(kv[1][kv[1].length-1]=='}')
          kv[1]=kv[1].substring(0,kv[1].length-1);
          if(kv[1][0]=='\'')
            kv[1]=kv[1].substring(1,kv[1].length-1);
           
          obj[kv[0]] = kv[1]
          }
          else{
            var http=item.substring(13,item.length-1);
            obj["goodPicture"]=http;
          }
          
      })
      return obj;
}

function stringtoObjWashed(str){
    var str2=str.substring(1,str.length-1);
    let obj = {};
    let arr1 = str2.split(', ');
    arr1.forEach(function(item){
       //每一项元素，再根据=号字符 打散成数组
       let kv = item.split('=');
          //每次向对象里面添加 属性和属性值
          //若值有引号则去掉
          if(kv.length==2){
            if(kv[1][kv[1].length-1]=='}')
          kv[1]=kv[1].substring(0,kv[1].length-1);
          if(kv[1][0]=='\'')
            kv[1]=kv[1].substring(1,kv[1].length-1);
           
          obj[kv[0]] = kv[1]
          }
          else{
            var http=item.substring(13,item.length-1);
            obj["goodPicture"]=http;
          }
          
      })
      return obj;
}
function cookietoObj(str){
    let obj = {};
    let arr1 = str.split('; ');
    arr1.forEach(function(item){
       //每一项元素，再根据=号字符 打散成数组
       let kv = item.split('=')
          //每次向对象里面添加 属性和属性值
          obj[kv[0]] = kv[1]
      })
      return obj;
}

function optionstoArray(str){
    let arr1 = str.split(',');
      return arr1;
}