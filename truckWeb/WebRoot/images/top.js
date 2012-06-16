var swf_width=1000;
var swf_height=293;
var files='images/index-03.jpg|images/index-03.jpg|images/index-03.jpg|images/index-03.jpg|images/index-03.jpg';
var links='||||';
var texts='||||';

document.write('<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"  codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" width="'+swf_width +'" height="'+ swf_height +'">');
document.write('<param name="movie" value="images/bcastr31.swf"><param name="quality" value="high">');
document.write('<param name="menu" value="false"><param name=wmode value="opaque">');
document.write('<param name="FlashVars" value="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'">');
//document.write('<embed src="images/bcastr31.swf" wmode="opaque" FlashVars="bcastr_file='+files+'&bcastr_link='+links+'&bcastr_title='+texts+'& menu="false" quality="high" width="'+ swf_width +'" height="'+ swf_height +'" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />'); 
document.write('</object>'); 
