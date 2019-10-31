/* jQuery Notice Plugin
 * 
 * This plug-in allows you to show a little notice box.
 * 
 * @author: Vincent Composieux
 * @homepage: http://vincent.composieux.fr
 * @date 20/06/2010
 */

(function($) {
	$.notice = {
		show: function(message,time,opType) {

			/** Configuration */
			var top = 100;
			var left = 47;
			var width = 100;
			var fontSize = 16;
			var fadeoutDuration = 1000;
			
			/** Launch the notification */
			$('html, body').animate({scrollTop:0});
			/*$('<div></div>').attr('id', 'notice').css('left', (50-left)+'%').css('bottom', (0+top)+'px').appendTo('body').text(message);*/
			$('<div></div>').attr('id', 'notice').css('width', width+'%').css('bottom', (0+top)+'px').css('font-size', fontSize+'px').appendTo('body').text(message);
			/** Switch off the notification */
			setTimeout(function() {
				$('#notice').animate({ opacity: 0, bottom: '-20px' }, fadeoutDuration);}, 3000);
			setTimeout(function() {$('#notice').remove(); back(opType);}, time);
		}
	}
	
	jQnotice = function(message,time,opType) { 

	$.notice.show(message,time,opType); };
})(jQuery);