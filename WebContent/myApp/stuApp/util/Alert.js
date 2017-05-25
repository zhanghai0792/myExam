Ext.define('core.util.Alert', {
			requires : ['core.util.Notification'],
			statics : {
				/*
				 * msgCt : null,
				 * 
				 * msg : function (title, format) {
				 * 
				 * function createBox (t, s) { return '<div class="msg"><h3>' +
				 * t + '</h3><p>' + s + '</p></div>'; }
				 * 
				 * if(!Packet.util.Alert.msgCt) { //Packt.util.Alert.msgCt =
				 * Ext.DomHelper.insertFirst(document.body, {id:'msg-div'},
				 * true); Packet.util.Alert.msgCt =
				 * Ext.DomHelper.append(Ext.getBody(), {id:'msg-div'}, true); }
				 * 
				 * var s = Ext.String.format.apply(String,
				 * Array.prototype.slice.call(arguments, 1)); var m =
				 * Ext.DomHelper.append(Packet.util.Alert.msgCt,
				 * createBox(title, s), true); m.hide();
				 * m.slideIn('t').ghost("t", { delay: 3000, remove: true}); }
				 */
				msg : function(title, msgContent) {
					Ext.widget('notification', {
								title : title,
								position : 'br',
								manager : Ext.getBody().id,
								iconCls : 'ux-notification-icon-information',
								autoCloseDelay : 4000,
								spacing : 20,
								width : 270,
								height : 150,
								html : msgContent
							}).show();
				}
			}
		});