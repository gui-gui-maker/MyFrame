function jqmIscrollviewRemoveLayerXYProps(a) {
	delete a.layerX;
	delete a.layerY
}
(function(g, j, o, i) {
	var r = o.ontouchend !== i, m = (/webkit/i).test(navigator.appVersion), a = (/android/gi)
			.test(navigator.appVersion), k = (/firefox/i)
			.test(navigator.userAgent), e = (/hp-tablet/gi)
			.test(navigator.appVersion), l = (/(iPhone|iPad|iPod).*AppleWebKit/)
			.test(navigator.appVersion), q = (/iPad.*AppleWebKit/)
			.test(navigator.appVersion), b = (/(iPhone|iPad|iPod).*AppleWebKit.*Safari/)
			.test(navigator.appVersion), f = (/(iPhone|iPad|iPod).*AppleWebKit.(?!.*Safari)/)
			.test(navigator.appVersion), s = l
			&& (j.navigator.Standalone !== i), d = g.mobile.ignoreContentEnabled === i, p = 1;
	function n(t) {
		t.preventDefault()
	}
	function c(u, w) {
		function t() {
		}
		t.prototype = w.prototype;
		var v = new t();
		v.constructor = u;
		u.prototype = v
	}
	function h(u, t, v) {
		this.iscrollview = u;
		this._emulateBottomOffset = function(w) {
			if (this.iscrollview.options.emulateBottomOffset) {
				this.maxScrollY = this.wrapperH - this.scrollerH
						+ this.minScrollY
						+ this.iscrollview.options.bottomOffset
			}
		};
		this._fixInput = function(y) {
			if (this.iscrollview.options.fixInput) {
				var w, x = y.target;
				while (x.nodeType !== 1) {
					x = x.parentNode
				}
				w = x.tagName.toLowerCase();
				if (w === "select" || w === "input" || w === "textarea") {
					return
				}
			}
			if (this.iscrollview.options.preventTouchHover) {
				y.stopImmediatePropagation()
			} else {
				y.preventDefault()
			}
		};
		this._doCallback = function(z, y, x) {
			if (typeof y === "object") {
				jqmIscrollviewRemoveLayerXYProps(y)
			}
			var w = this.iscrollview, A = w._logCallback(z, y);
			if (x) {
				x.call(this, y)
			}
			w._trigger(z.toLowerCase(), y, {
				iscrollview : w
			});
			w._logCallback(z, y, A)
		};
		this._bind = function(z, y, w) {
			var A = this.iscrollview.options.bindIscrollUsingJqueryEvents, x = A
					&& z === "mouseout" ? "mouseleave" : z;
			if (z === "orientationchange" || z === "resize") {
				this.iscrollview._logIscrollEvent("iScroll bind (ignored)", z);
				return
			}
			this.iscrollview._logIscrollEvent("iScroll bind", z);
			if (A) {
				(y ? g(y) : this.iscrollview.$scroller).bind(x, g.proxy(
						this.handleEvent, this))
			} else {
				(y || this.scroller).addEventListener(x, this, !!w)
			}
		};
		this._unbind = function(z, y, w) {
			var A = this.iscrollview.options.bindIscrollUsingJqueryEvents, x = A
					&& z === "mouseout" ? "mouseleave" : z;
			if (z === "orientationchange" || z === "resize") {
				this.iscrollview._logIscrollEvent("iScroll unbind (ignored)");
				return
			}
			this.iscrollview._logIscrollEvent("iScroll unbind", z);
			if (A) {
				g(y || this.iscrollview.$scroller).unbind(x, this.handleEvent)
			} else {
				(y || this.scroller).removeEventListener(x, this, !!w)
			}
		};
		this._origHandleEvent = iScroll.prototype.handleEvent;
		this.handleEvent = function(x) {
			var w = this.iscrollview.options.bindIscrollUsingJqueryEvents, y;
			y = this.iscrollview._logIscrollEvent("iScroll.handleEvent", x);
			if (w && x.type === "mouseleave") {
				x.type = "mouseout";
				this._origHandleEvent(x);
				x.type = "mouseleave"
			} else {
				this._origHandleEvent(x)
			}
			this.iscrollview._logIscrollEvent("iScroll.handleEvent", x, y)
		};
		this._resize = function() {
		};
		iScroll.call(this, t, v)
	}
	c(h, iScroll);
	g
			.widget(
					"mobile.iscrollview",
					g.mobile.widget,
					{
						widgetEventPrefix : "iscroll_",
						iscroll : null,
						$window : g(j),
						$wrapper : [],
						$scroller : [],
						$scrollerContent : [],
						$pullDown : [],
						$pullUp : [],
						$pullUpSpacer : [],
						$page : [],
						_wrapperHeightAdjustForBoxModel : 0,
						_firstScrollerExpand : true,
						createdAt : null,
						pageID : null,
						instanceID : null,
						_dirty : false,
						_dirtyCallbackBefore : null,
						_dirtyCallbackAfter : null,
						_sizeDirty : false,
						options : {
							hScroll : false,
							hScrollbar : false,
							debug : false,
							traceResizeWrapper : false,
							traceRefresh : false,
							traceCreateDestroy : false,
							traceIscrollEvents : false,
							tracedIscrollEvents : [],
							traceWidgetEvents : false,
							tracedWidgetEvents : [],
							traceIscrollCallbacks : false,
							tracedIscrollCallbacks : [],
							traceWidgetCallbacks : false,
							tracedWidgetCallbacks : [],
							bottomOffset : 0,
							emulateBottomOffset : true,
							pageClass : "iscroll-page",
							wrapperClass : "iscroll-wrapper",
							scrollerClass : "iscroll-scroller",
							pullDownClass : "iscroll-pulldown",
							pullUpClass : "iscroll-pullup",
							pullLabelClass : "iscroll-pull-label",
							pullUpSpacerClass : "iscroll-pullup-spacer",
							topSpacerClass : "iscroll-top-spacer",
							bottomSpacerClass : "iscroll-bottom-spacer",
							scrollerContentClass : "iscroll-content",
							fixedHeightClass : "iscroll-fixed",
							fixedHeightSelector : ":jqmData(role='header'), :jqmData(role='footer'), :jqmData(iscroll-fixed)",
							resizeWrapper : true,
							resizeEvents : "resize"
									+ (g.support.orientation ? " orientationchange"
											: ""),
							refreshOnPageBeforeShow : false,
							fixInput : true,
							wrapperAdd : 0,
							refreshDelay : a ? 200 : 0,
							scrollShortContent : true,
							removeWrapperPadding : true,
							addScrollerPadding : true,
							addSpacers : true,
							scrollTopOnResize : true,
							scrollTopOnOrientationChange : true,
							createScroller : true,
							deferNonActiveRefresh : true,
							deferNonActiveResize : true,
							preventTouchHover : d && r,
							bindIscrollUsingJqueryEvents : false,
							fastDestroy : false,
							preventPageScroll : true,
							pullDownResetText : "下拉加载数据",
							pullDownPulledText : "释放加载数据",
							pullDownLoadingText : "数据加载中，请稍候……",
							pullUpResetText : "上拉加载数据",
							pullUpPulledText : "释放加载数据",
							pullUpLoadingText : "数据加载中，请稍候……",
							pullPulledClass : "iscroll-pull-pulled",
							pullLoadingClass : "iscroll-pull-loading",
							onrefresh : null,
							onbeforescrollstart : null,
							onscrollstart : null,
							onbeforescrollmove : null,
							onscrollmove : null,
							onbeforescrollend : null,
							onscrollend : null,
							ontouchend : null,
							ondestroy : null,
							onzoomstart : null,
							onzoom : null,
							onzoomend : null,
							onpulldownreset : null,
							onpulldownpulled : null,
							onpulldown : null,
							onpullupreset : null,
							onpulluppulled : null,
							onpullup : null,
							onbeforerefresh : null,
							onafterrefresh : null
						},
						_widgetOnlyOptions : [ "debug", "traceIscrollEvents",
								"tracedIscrollEvents", "traceIscrollCallbacks",
								"tracedIscrollCallbacks", "traceWidgetEvents",
								"tracedWidgetEvents", "traceWidgetCallbacks",
								"tracedWidgetCallbacks", "traceResizeWrapper",
								"traceRefresh", "traceCreateDestroy",
								"bottomOffset", "emulateBottomOffset",
								"pageClass", "wrapperClass", "scrollerClass",
								"pullDownClass", "pullUpClass",
								"scrollerContentClass", "pullLabelClass",
								"pullUpSpacerClass", "topSpacerClass",
								"bottomSpacerClass", "addSpacer",
								"fixedHeightSelector", "resizeWrapper",
								"resizeEvents", "refreshOnPageBeforeShow",
								"fixInput", "wrapperAdd", "refreshDelay",
								"scrollShortContent", "removeWrapperPadding",
								"addScrollerPadding", "createScroller",
								"deferNonActiveRefresh", "preventTouchHover",
								"deferNonActiveResize",
								"bindIscrollUsingJqueryEvents",
								"scrollTopOnResize",
								"scrollTopOnOrientationChange",
								"pullDownResetText", "pullDownPulledText",
								"pullDownLoadingText", "pullUpResetText",
								"pullUpPulledText", "pullUpLoadingText",
								"pullPulledClass", "pullLoadingClass",
								"onpulldownreset", "onpulldownpulled",
								"onpulldown", "onpullupreset",
								"onpulluppulled", "onpullup",
								"onbeforerefresh", "onafterrefresh",
								"fastDestroy", "preventPageScroll" ],
						_event_map : {
							onrefresh : "onRefresh",
							onbeforescrollstart : "onBeforeScrollStart",
							onscrollstart : "onScrollStart",
							onbeforescrollmove : "onBeforeScrollMove",
							onscrollmove : "onScrollMove",
							onbeforescrollend : "onBeforeScrollEnd",
							onscrollend : "onScrollEnd",
							ontouchend : "onTouchEnd",
							ondestroy : "onDetroy",
							onzoomstart : "onZoomStart",
							onzoom : "onZoom",
							onzoomend : "onZoomEnd"
						},
						_proxy_event_funcs : {
							onRefresh : function(t) {
								this._doCallback("onRefresh", t, function(u) {
									this._emulateBottomOffset();
									this.iscrollview._pullOnRefresh.call(
											this.iscrollview, u)
								})
							},
							onBeforeScrollStart : function(t) {
								this._doCallback("onBeforeScrollStart", t,
										function(u) {
											this._fixInput(u)
										})
							},
							onScrollStart : function(t) {
								this._doCallback("onScrollStart", t)
							},
							onBeforeScrollMove : function(t) {
								this._doCallback("onBeforeScrollMove", t);
								t.preventDefault()
							},
							onScrollMove : function(t) {
								this._doCallback("onScrollMove", t,
										function(u) {
											this.iscrollview._pullOnScrollMove
													.call(this.iscrollview, u)
										})
							},
							onBeforeScrollEnd : function(t) {
								this._doCallback("onBeforeScrollEnd", t)
							},
							onScrollEnd : function(t) {
								this._doCallback("onScrollEnd", t, function(u) {
									this.iscrollview._pullOnScrollEnd.call(
											this.iscrollview, u)
								})
							},
							onTouchEnd : function(t) {
								this._doCallback("onTouchEnd", t)
							},
							onDestroy : function(t) {
								this._doCallback("onDestroy", t)
							},
							onZoomStart : function(t) {
								this._doCallback("onZoomStart", t)
							},
							onZoom : function(t) {
								this._doCallback("onZoom", t)
							},
							onZoomEnd : function(t) {
								this._doCallback("onZoomEnd", t)
							}
						},
						_merge_from_iscroll_options : function() {
							var t = g.extend(true, {}, this.iscroll.options);
							g.each(this._proxy_event_funcs, function(w, u) {
								delete t[w]
							});
							if (this.options.emulateBottomOffset) {
								delete t.bottomOffset
							}
							g.extend(this.options, t)
						},
						_create_iscroll_options : function() {
							var t = g.extend(true, {}, this.options);
							g.each(this._widgetOnlyOptions, function(w, u) {
								delete t[u]
							});
							g.each(this._event_map, function(w, u) {
								delete t[w]
							});
							if (this.options.emulateBottomOffset) {
								delete t.bottomOffset
							}
							return g.extend(t, this._proxy_event_funcs)
						},
						_pad : function(t, v, w) {
							var x = t.toString(), u = w || "0";
							while (x.length < v) {
								x = u + x
							}
							return x
						},
						_toTime : function(t) {
							return this._pad(t.getHours(), 2) + ":"
									+ this._pad(t.getMinutes(), 2) + ":"
									+ this._pad(t.getSeconds(), 2) + "."
									+ this._pad(t.getMilliseconds(), 3)
						},
						_log : function(w, t) {
							var u, x, v;
							if (!this.options.debug) {
								return null
							}
							u = t || new Date();
							x = this.$wrapper.attr("id");
							v = x ? "#" + x : "";
							console.log(this._toTime(u)
									+ " "
									+ g.mobile.path.parseUrl(this.$page
											.jqmData("url")).filename + v + " "
									+ w);
							return u
						},
						_logInterval : function(v, u) {
							var t;
							if (!this.options.debug) {
								return null
							}
							t = new Date();
							return this._log(v + " " + (t - u) + "mS from "
									+ this._toTime(u), t)
						},
						_logEvent : function(A, y, z) {
							var t, x, w = y && y instanceof Object, u = w ? y.type
									: y, v = u + " " + A;
							if (!this.options.debug) {
								return null
							}
							t = new Date();
							if (z) {
								v += " end " + (+(t - z)) + "mS from "
										+ this._toTime(z)
							} else {
								if (w) {
									v += " begin"
								}
							}
							if (w) {
								x = new Date(y.timeStamp);
								v += " (" + (t - x) + "mS from " + y.type
										+ " @ " + this._toTime(x) + ")"
							}
							return this._log(v, t)
						},
						_logCallback : function(u, t, v) {
							if (!this.options.debug
									|| !this.options.traceIscrollCallbacks
									|| (this.options.tracedIscrollCallbacks.length !== 0 && g
											.inArray(
													u,
													this.options.tracedIscrollCallbacks) === -1)) {
								return null
							}
							if (t) {
								return this._logEvent(u, t, v)
							}
							if (v) {
								return this._logInterval(u + " end", v)
							}
							return this._log(u + " begin")
						},
						_logIscrollEvent : function(x, v, w) {
							var u = v instanceof Event, t = u ? v.type : v;
							if (!this.options.debug
									|| !this.options.traceIscrollEvents
									|| (this.options.tracedIscrollEvents.length !== 0 && g
											.inArray(
													t,
													this.options.tracedIscrollEvents) === -1)) {
								return null
							}
							return this._logEvent(x, v, w)
						},
						_logWidgetEvent : function(x, v, w) {
							var u = v instanceof Object, t = u ? v.type : v;
							if (!this.options.debug
									|| !this.options.traceWidgetEvents
									|| (this.options.tracedWidgetEvents.length !== 0 && g
											.inArray(
													t,
													this.options.tracedWidgetEvents) === -1)) {
								return null
							}
							return this._logEvent(x, v, w)
						},
						_logWidgetCallback : function(u, t, v) {
							if (!this.options.debug
									|| !this.options.traceWidgetCallbacks
									|| (this.options.tracedWidgetCallbacks.length !== 0 && g
											.inArray(
													u,
													this.options.tracedWidgetCallbacks) === -1)) {
								return null
							}
							if (t) {
								return this._logEvent(u, t, v)
							}
							if (v) {
								return this._logInterval(u + " end", v)
							}
							return this._log(u + " begin")
						},
						_logInterval2 : function(w, v, t) {
							var u;
							if (!this.options.debug) {
								return
							}
							u = new Date();
							this._log(w + " " + (u - t) + "mS from "
									+ this._toTime(t) + " (" + (u - v)
									+ "mS from " + this._toTime(v) + ")")
						},
						_startTiming : function() {
							if (!this.options.debug) {
								return null
							}
							return new Date()
						},
						_pageEventNamespace : function() {
							return ".iscroll_" + this.pageID
						},
						_instanceEventNamespace : function() {
							return this._pageEventNamespace() + "_"
									+ this.instanceID
						},
						_addEventsNamespace : function(t, v) {
							var u = t.split(" ");
							g.each(u, function(x, w) {
								u[x] += v
							});
							return u.join(" ")
						},
						_isvBind : function(x, t, v, w) {
							var u = this._addEventsNamespace(t, this
									._instanceEventNamespace());
							this._logWidgetEvent("bind " + w, u);
							x.bind(u, g.proxy(v, this))
						},
						_bindPage : function(t, v) {
							var u = this._addEventsNamespace(t, this
									._pageEventNamespace());
							this._logWidgetEvent("bind $page", u);
							this.$page.bind(u, g.proxy(v, this))
						},
						_isvUnbind : function(w, t, v) {
							var u = this._addEventsNamespace(t, this
									._instanceEventNamespace());
							this._logWidgetEvent("unbind " + v, u);
							w.unbind(u)
						},
						_unbindPage : function(t) {
							var u = this._addEventsNamespace(t, this
									._pageEventNamespace());
							this._logWidgetEvent("unbind  $page", u);
							this.$page.unbind(u)
						},
						_delegate : function(x, t, u, v, w) {
							this._logWidgetEvent("delegate " + w + " " + t, u);
							x.delegate(t, u, g.proxy(v, this))
						},
						_triggerWidget : function(t, u) {
							var v = this._logWidgetCallback(t);
							this._trigger(t, u, {
								iscrollview : this
							});
							this._logWidgetCallback(t, u, v)
						},
						isDirty : function() {
							return this._dirty
						},
						_restoreStyle : function(t, u) {
							if (u === i) {
								return
							}
							if (u === null) {
								t.removeAttr("style")
							} else {
								t.attr("style", u)
							}
						},
						_pageBeforeShowFunc : function(t) {
							var u = this._logWidgetEvent("_pageBeforeShowFunc",
									t);
							if (this._dirty) {
								this.resizeWrapper(true);
								this.refresh(null, this._dirtyCallbackBefore,
										this._dirtyCallbackAfter, true);
								this._dirty = false;
								this._dirtyCallbackBefore = null;
								this._dirtyCallbackAfter = null
							} else {
								if (this.options.refreshOnPageBeforeShow
										|| this._sizeDirty) {
									this.refresh(null, g.proxy(
											this._resizeWrapper, this), null,
											true)
								}
							}
							this._sizeDirty = false;
							this._logWidgetEvent("_pageBeforeShowFunc", t, u)
						},
						_windowResizeFunc : function(t) {
							var u = this
									._logWidgetEvent("_windowResizeFunc", t);
							if (this.options.deferNonActiveResize
									&& !(this.$page.is(g.mobile.activePage))) {
								this._sizeDirty = true;
								if (this.options.traceResizeWrapper) {
									this._log("resizeWrapper() (deferred)")
								}
							} else {
								this.resizeWrapper(true);
								this.refresh(null, null, null, true)
							}
							this._logWidgetEvent("_windowResizeFunc", t, u)
						},
						_orientationChangeFunc : function(t) {
							var u = this._logWidgetEvent(
									"_orientationChangeFunc", t);
							if (this.options.scrollTopOnOrientationChange) {
								g.mobile.silentScroll(0)
							}
							this
									._logWidgetEvent("_orientationChangeFunc",
											t, u)
						},
						_updateLayoutFunc : function(t) {
							this.refresh()
						},
						_instanceCount : function(u) {
							var t = "iscroll-private", v = 0, w = this.$page
									.jqmData(t)
									|| {};
							if (u !== i) {
								v = u;
								w.instanceCount = v;
								this.$page.jqmData(t, w)
							} else {
								if (w.instanceCount !== i) {
									v = w.instanceCount
								}
							}
							return v
						},
						_nextInstanceID : function(u) {
							var t = "iscroll-private", w = 1, v = this.$page
									.jqmData(t)
									|| {};
							if (u !== i) {
								w = u;
								v.nextInstanceID = w;
								this.$page.jqmData(t, v)
							} else {
								if (v.nextInstanceID !== i) {
									w = v.nextInstanceID
								}
							}
							return w
						},
						_pageID : function(u) {
							var t = "iscroll-private", w = 1, v = this.$page
									.jqmData(t)
									|| {};
							if (u !== i) {
								w = u;
								v.pageID = w;
								this.$page.jqmData(t, v)
							} else {
								if (v.pageID !== i) {
									w = v.pageID
								}
							}
							return w
						},
						_adaptPage : function() {
							var t = this;
							if (this._instanceCount() === 1) {
								this.$page.addClass(this.options.pageClass);
								this.$page
										.find(this.options.fixedHeightSelector)
										.each(
												function() {
													var w = g(this), v = w
															.closest(".ui-popup").length !== 0, u = w
															.closest(".ui-panel").length !== 0;
													if (!v && !u) {
														w
																.addClass(t.options.fixedHeightClass)
													}
												});
								if (r && this.options.preventPageScroll) {
									this._bindPage("touchmove", n)
								}
							}
						},
						_undoAdaptPage : function() {
							var t = this;
							if (this._instanceCount() === 1) {
								this.$page.find(
										this.options.fixedHeightSelector).each(
										function() {
											g(this).removeClass(
													t.options.fixedHeightClass)
										});
								this.$page.removeClass(this.options.pageClass)
							}
						},
						_calculateBarsHeight : function() {
							var w = 0, v = "." + this.options.fixedHeightClass, t = this.$page
									.find(v), u = g(".ui-mobile-viewport")
									.children(v);
							t.each(function() {
								w += g(this).outerHeight(true)
							});
							u.each(function() {
								var x = g(this).jqmData("id");
								if (x === ""
										|| !t.is(":jqmData(id='" + x + "')")) {
									w += g(this).outerHeight(true)
								}
							});
							return w
						},
						_getBoxSizing : function(t) {
							var v, u = "";
							if (k) {
								u = "-moz-"
							} else {
								if (m) {
									u = "-webkit-"
								}
							}
							v = t.css(u + "box-sizing");
							if (!v && u) {
								v = t.css("box-sizing")
							}
							if (!v) {
								if (g.boxModel) {
									v = "content-box"
								} else {
									v = "border-box"
								}
							}
							return v
						},
						_getHeightAdjustForBoxModel : function(t) {
							var u;
							switch (this._getBoxSizing(t)) {
							case "border-box":
								u = t.outerHeight(true) - t.outerHeight();
								break;
							case "padding-box":
								u = t.outerHeight() - t.height();
								break;
							case "content-box":
							default:
								u = t.outerHeight(t !== this.$wrapper)
										- t.height();
								break
							}
							return u
						},
						_setTopOffsetForPullDown : function() {
							if (this.$pullDown.length
									&& !this.options.topOffset) {
								this.options.topOffset = this.$pullDown
										.outerHeight(true)
							}
						},
						_setBottomOffsetForPullUp : function() {
							if (this.$pullUp.length
									&& !this.options.bottomOffset) {
								this.options.bottomOffset = this.$pullUp
										.outerHeight(true)
							}
						},
						_removeWrapperPadding : function() {
							var t = this.$wrapper;
							if (this.options.removeWrapperPadding) {
								this._origWrapperPaddingLeft = t
										.css("padding-left");
								this._origWrapperPaddingRight = t
										.css("padding-right");
								this._origWrapperPaddingTop = t
										.css("padding-top");
								this._origWrapperPaddingBottom = t
										.css("padding-bottom");
								this.$wrapper.css("padding", 0)
							}
						},
						_modifyWrapperCSS : function() {
							this._origWrapperStyle = this.$wrapper
									.attr("style")
									|| null;
							this._removeWrapperPadding()
						},
						_undoModifyWrapperCSS : function() {
							this._restoreStyle(this.$wrapper,
									this._origWrapperStyle)
						},
						_addScrollerPadding : function() {
							if (this.options.removeWrapperPadding
									&& this.options.addScrollerPadding) {
								var t, v = this.$scroller.children(), u = v
										.not(this.$pullDown).not(this.$pullUp)
										.not(this.$pullUpSpacer);
								u.wrapAll("<div/>");
								t = u.parent().addClass(
										this.options.scrollerContentClass);
								t
										.css({
											"padding-left" : this._origWrapperPaddingLeft,
											"padding-right" : this._origWrapperPaddingRight,
											"padding-top" : this._origWrapperPaddingTop,
											"padding-bottom" : this._origWrapperPaddingBottom
										})
							}
						},
						_undoAddScrollerPadding : function() {
							if (this.options.removeWrapperPadding
									&& this.options.addScrollerPadding) {
								g("." + this.options.scrollerContentClass,
										this.$scroller).children().unwrap()
							}
						},
						_addWrapperClasses : function() {
							this.$wrapper.addClass(this.options.wrapperClass);
							this.$scroller.addClass(this.options.scrollerClass)
						},
						_undoAddWrapperClasses : function() {
							this.$scroller
									.removeClass(this.options.scrollerClass);
							this.$wrapper
									.removeClass(this.options.wrapperClass)
						},
						_expandScrollerToFillWrapper : function() {
							if (this.options.scrollShortContent
									|| this.$pullDown.length
									|| this.$pullUp.length) {
								if (this._firstScrollerExpand) {
									this._origScrollerStyle = this.$scroller
											.attr("style")
											|| null;
									this._firstScrollerExpand = false
								}
								this.$scroller
										.css(
												"min-height",
												this.$wrapper.height()
														+ (this.$pullDown.length ? this.$pullDown
																.outerHeight(true)
																: 0)
														+ (this.$pullUp.length ? this.$pullUp
																.outerHeight(true)
																: 0))
								//todo 列表宽度发生变化的情况
								this.$scroller.css("width","100%");
							}
						},
						_undoExpandScrollerToFillWrapper : function() {
							this._restoreStyle(this.$scroller,
									this._origScrollerStyle)
						},
						_resizeWrapper : function(v) {
							var x = null, t, w, u;
							if (!v && !this.options.resizeWrapper) {
								return
							}
							if (this.options.traceResizeWrapper) {
								x = this._log("resizeWrapper() start")
							}
							this.$wrapper.trigger("updatelayout");
							t = o.documentElement.clientHeight;
							w = this._calculateBarsHeight();
							u = t - w - this._wrapperHeightAdjustForBoxModel
									+ (b && !q ? 60 : 0)
									+ this.options.wrapperAdd;
							this.$wrapper.css("height", u);
							this._expandScrollerToFillWrapper();
							if (this.options.traceResizeWrapper) {
								this._logInterval("resizeWrapper() end"
										+ (this._sizeDirty ? " (dirty)" : ""),
										x)
							}
						},
						resizeWrapper : function(t) {
							var u = this._setPageVisible();
							this._resizeWrapper(t !== i);
							this._restorePageVisibility(u)
						},
						_undoResizeWrapper : function() {
						},
						_modifyWrapper : function() {
							this._addWrapperClasses();
							this._modifyWrapperCSS();
							this._wrapperHeightAdjustForBoxModel = this
									._getHeightAdjustForBoxModel(this.$wrapper)
						},
						_undoModifyWrapper : function() {
							this._undoResizeWrapper();
							this._undoModifyWrapperCSS();
							this._undoAddWrapperClasses()
						},
						_modifyPullDown : function() {
							var t, u, v;
							if (this.$pullDown.length === 0) {
								return
							}
							t = g("." + this.options.pullLabelClass,
									this.$pullDown);
							if (t.length) {
								this._origPullDownLabelText = t.text();
								if (this._origPullDownLabelText) {
									this.options.pullDownResetText = this._origPullDownLabelText
								} else {
									t.text(this.options.pullDownResetText)
								}
								u = t.jqmData("iscroll-pulled-text");
								if (u) {
									this.options.pullDownPulledText = u
								}
								v = t.jqmData("iscroll-loading-text");
								if (v) {
									this.options.pullDownLoadingText = v
								}
							}
						},
						_undoModifyPullDown : function() {
							if (this.$pullDown.length === 0) {
								return
							}
							var t = g("." + this.options.pullLabelClass,
									this.$pullDown);
							if (t.length === 0) {
								return
							}
							t.text(this._origPullDownLabelText)
						},
						_modifyPullUp : function() {
							var v, t, u;
							if (this.$pullUp.length === 0) {
								return
							}
							g("<div></div>").insertBefore(this.$pullUp).css(
									"height", this.$pullUp.outerHeight(true));
							this.$pullUpSpacer = this.$pullUp.prev();
							this.$pullUpSpacer
									.addClass(this.options.pullUpSpacerClass);
							v = g("." + this.options.pullLabelClass,
									this.$pullUp);
							if (v.length) {
								this._origPullUpLabelText = v.text();
								if (this._origPullUpLabelText) {
									this.options.pullUpResetText = this._origPullUpLabelText
								} else {
									v.text(this.options.pullUpResetText)
								}
								t = v.jqmData("iscroll-pulled-text");
								if (t) {
									this.options.pullUpPulledText = t
								}
								u = v.jqmData("iscroll-loading-text");
								if (u) {
									this.options.pullUpLoadingText = u
								}
							}
						},
						_undoModifyPullUp : function() {
							if (this.$pullUp.length === 0) {
								return
							}
							this.$pullUp.prev().remove();
							if (this._origPullUpLabelText) {
								g("." + this.options.pullLabelClass,
										this.$pullUp).text(
										this._origPullUpLabelText)
							}
						},
						_correctPushedDownPage : function() {
							if (this.options.resizeWrapper
									&& this.options.scrollTopOnResize) {
								g.mobile.silentScroll(0)
							}
						},
						refresh : function(y, t, v, x) {
							var z, B, u, C, A, w;
							if (!x && this.options.deferNonActiveRefresh
									&& !(this.$page.is(g.mobile.activePage))) {
								this._dirty = true;
								this._dirtyCallbackBefore = t;
								this._dirtyCallbackAfter = v;
								if (this.options.traceRefresh) {
									this._log("refresh() (deferred)")
								}
								return
							}
							z = this;
							B = y;
							u = t;
							C = v;
							A = x;
							w = this._startTiming();
							if ((B === i) || (B === null)) {
								B = this.options.refreshDelay
							}
							setTimeout(function() {
								var D = null, E;
								if (z.options.traceRefresh) {
									D = z._logInterval("refresh() start", w)
								}
								E = z._setPageVisible();
								if (u) {
									u()
								}
								z._triggerWidget("onbeforerefresh");
								z.iscroll.refresh();
								z._triggerWidget("onafterrefresh");
								if (C) {
									C()
								}
								z._restorePageVisibility(E);
								if (!E) {
									z._correctPushedDownPage()
								}
								if (z.options.traceRefresh) {
									z._logInterval2("refresh() end"
											+ (A ? " (dirty)" : ""), w, D)
								}
							}, B);
							if (this.options.traceRefresh) {
								this._log("refresh() will occur after >= " + B
										+ "mS")
							}
						},
						_create_iscroll_object : function() {
							this.iscroll = new h(this, this.$wrapper.get(0),
									this._create_iscroll_options())
						},
						_createScroller : function() {
							if (this.options.createScroller) {
								if (this.$wrapper.children().length) {
									this.$wrapper.children().wrapAll("<div/>")
								} else {
									this.$wrapper
											.append("<div><div></div></div>")
								}
							}
						},
						_undoCreateScroller : function() {
							if (this.options.createScroller) {
								this.$scroller.children().unwrap()
							}
						},
						_addSpacers : function() {
							if (this.options.addSpacers) {
								this.$scrollerContent.before(g('<div class="'
										+ this.options.topSpacerClass
										+ '"></div>'));
								this.$scrollerContent.after(g('<div class="'
										+ this.options.bottomSpacerClass
										+ '"></div>'))
							}
						},
						_undoAddSpacers : function() {
							this.$wrapper.find(topSpacerClass).remove();
							this.$wrapper.find(bottomSpacerClass).remove()
						},
						_setPageVisible : function() {
							var t = this.$page.is(":hidden");
							if (t) {
								this.$page.css("display", "block")
							}
							return t
						},
						_restorePageVisibility : function(t) {
							if (t) {
								this.$page.css("display", "")
							}
						},
						_create : function() {
							var u = new Date(), t;
							this.$wrapper = this.element;
							this.$page = this.$wrapper
									.parents(":jqmData(role='page')");
							g.extend(true, this.options, this.$wrapper
									.jqmData("iscroll"));
							if (this.options.debug
									&& this.options.traceCreateDestroy) {
								this._log("_create() start", u)
							}
							this.createdAt = u;
							this._instanceCount(this._instanceCount() + 1);
							this.instanceID = this._nextInstanceID();
							this._nextInstanceID(this.instanceID + 1);
							if (this.instanceID === 1) {
								this._pageID(p);
								p += 1
							}
							this.pageID = this._pageID();
							t = this._setPageVisible();
							this._adaptPage();
							this._createScroller();
							this.$scroller = this.$wrapper.children(":first");
							if (this.$scroller.length === 0) {
								return
							}
							this.$pullDown = g(
									"." + this.options.pullDownClass,
									this.$scroller);
							this._modifyPullDown();
							this.$pullUp = g("." + this.options.pullUpClass,
									this.$scroller);
							this._modifyPullUp();
							this._modifyWrapper();
							this._bindPage("pagebeforeshow",
									this._pageBeforeShowFunc);
							this._setTopOffsetForPullDown();
							this._setBottomOffsetForPullUp();
							this._resizeWrapper();
							this._addScrollerPadding();
							this.$scrollerContent = this.$scroller.find("."
									+ this.options.scrollerContentClass);
							this._addSpacers();
							this._create_iscroll_object();
							this._merge_from_iscroll_options();
							this._restorePageVisibility(t);
							if (this.options.resizeWrapper) {
								this._isvBind(this.$window,
										this.options.resizeEvents,
										this._windowResizeFunc, "$window");
								if (this.options.scrollTopOnOrientationChange) {
									this._isvBind(this.$window,
											"orientationchange",
											this._orientationChangeFunc,
											"$window")
								}
							}
							this._isvBind(this.$scrollerContent,
									"updatelayout", this._updateLayoutFunc,
									"$scrollerContent");
							if (this.options.debug
									&& this.options.traceCreateDestroy) {
								this._logInterval("_create() end", u)
							}
						},
						destroy : function() {
							var t = null;
							if (this.options.debug
									&& this.options.traceCreateDestroy) {
								t = this._log("destroy() start")
							}
							this._isvUnbind(this.$scrollerContent,
									"updatelayout", "$scrollerContent");
							this._isvUnbind(this.$window,
									this.options.resizeEvents, "$window");
							this._isvUnbind(this.$window, "orientationchange",
									"$window");
							if (this._instanceCount() === 1) {
								this._unbindPage("pagebeforeshow");
								if (r) {
									this._unbindPage("touchmove")
								}
							}
							if (!this.options.fastDestroy) {
								this.iscroll.destroy();
								this.iscroll = null;
								this._undoExpandScrollerToFillWrapper();
								this._undoModifyPullDown();
								this._undoModifyPullUp();
								this._undoAddSpacers();
								this._undoAddScrollerPadding();
								this._undoModifyWrapper();
								this.$wrapper
										.removeClass(this.options.wrapperClass);
								this.$scroller
										.removeClass(this.options.scrollerClass);
								this._undoCreateScroller()
							}
							this._instanceCount(this._instanceCount() - 1);
							if (this._instanceCount() === 0) {
								this._undoAdaptPage()
							}
							g.Widget.prototype.destroy.call(this);
							if (this.options.debug
									&& this.options.traceCreateDestroy) {
								this._logInterval("destroy() end", t)
							}
						},
						enable : function() {
							this.iscroll.enable();
							g.Widget.prototype.enable.call(this)
						},
						disable : function() {
							this.iscroll.disable();
							g.Widget.prototype.disable.call(this)
						},
						_setOption : function(t, v) {
							var u;
							this.options[t] = v;
							this.iscroll.destroy();
							u = this._setPageVisible();
							this._create_iscroll_object();
							this._restorePageVisibility(u);
							g.Widget.prototype._setOption
									.apply(this, arguments)
						},
						scrollTo : function(t, w, v, u) {
							this.iscroll.scrollTo(t, w, v, u)
						},
						scrollToElement : function(t, u) {
							this.iscroll.scrollToElement(t, u)
						},
						scrollToPage : function(u, t, v) {
							this.iscroll.scrollToPage(u, t, v)
						},
						stop : function() {
							this.iscroll.stop()
						},
						zoom : function(t, w, v, u) {
							this.iscroll.zoom(t, w, v, u)
						},
						isReady : function() {
							return this.iscroll.isReady()
						},
						x : function() {
							return this.iscroll.x
						},
						y : function() {
							return this.iscroll.y
						},
						wrapperW : function() {
							return this.iscroll.wrapperW
						},
						wrapperH : function() {
							return this.iscroll.wrapperH
						},
						scrollerW : function() {
							return this.iscroll.scrollerW
						},
						scrollerH : function() {
							return this.iscroll.scrollerH
						},
						minScrollX : function(t) {
							if (t !== i) {
								this.iscroll.minScrollX = t
							}
							return this.iscroll.minScrollX
						},
						minScrollY : function(t) {
							if (t !== i) {
								this.iscroll.minScrollY = t
							}
							return this.iscroll.minScrollY
						},
						maxScrollX : function(t) {
							if (t !== i) {
								this.iscroll.maxScrollX = t
							}
							return this.iscroll.maxScrollX
						},
						maxScrollY : function(t) {
							if (t !== i) {
								this.iscroll.maxScrollY = t
							}
							return this.iscroll.maxScrollY
						},
						_pullDownIsPulled : function() {
							return this.$pullDown.length
									&& this.$pullDown
											.hasClass(this.options.pullPulledClass)
						},
						_pullUpIsPulled : function() {
							return this.$pullUp.length
									&& this.$pullUp
											.hasClass(this.options.pullPulledClass)
						},
						_replacePullText : function(u, v) {
							var t;
							if (v) {
								t = g("." + this.options.pullLabelClass, u);
								if (t) {
									t.text(v)
								}
							}
						},
						_pullSetStateReset : function(u, v) {
							if (u.is("." + this.options.pullLoadingClass
									+ ", ." + this.options.pullPulledClass)) {
								var w = u.find(".iscroll-pull-icon"), t = w
										.clone();
								u.removeClass(this.options.pullPulledClass
										+ " " + this.options.pullLoadingClass);
								this._replacePullText(u, v);
								w.replaceWith(t)
							}
						},
						_pullDownSetStateReset : function(t) {
							this._pullSetStateReset(this.$pullDown,
									this.options.pullDownResetText);
							this._triggerWidget("onpulldownreset", t)
						},
						_pullUpSetStateReset : function(t) {
							this._pullSetStateReset(this.$pullUp,
									this.options.pullUpResetText);
							this._triggerWidget("onpullupreset", t)
						},
						_pullSetStatePulled : function(t, u) {
							t.removeClass(this.options.pullLoadingClass)
									.addClass(this.options.pullPulledClass);
							this._replacePullText(t, u)
						},
						_pullDownSetStatePulled : function(t) {
							this._pullSetStatePulled(this.$pullDown,
									this.options.pullDownPulledText);
							this._triggerWidget("onpulldownpulled", t)
						},
						_pullUpSetStatePulled : function(t) {
							this._pullSetStatePulled(this.$pullUp,
									this.options.pullUpPulledText);
							this._triggerWidget("onpulluppulled", t)
						},
						_pullSetStateLoading : function(t, u) {
							t.removeClass(this.options.pullPulledClass)
									.addClass(this.options.pullLoadingClass);
							this._replacePullText(t, u)
						},
						_pullDownSetStateLoading : function(t) {
							this._pullSetStateLoading(this.$pullDown,
									this.options.pullDownLoadingText);
							this._triggerWidget("onpulldownloading", t)
						},
						_pullUpSetStateLoading : function(t) {
							this._pullSetStateLoading(this.$pullUp,
									this.options.pullUpLoadingText);
							this._triggerWidget("onpulluploading", t)
						},
						_pullOnRefresh : function(t) {
							if (this.$pullDown.length) {
								this._pullDownSetStateReset(t)
							}
							if (this.$pullUp.length) {
								this._pullUpSetStateReset(t)
							}
						},
						_pullOnScrollMove : function(A) {
							var u, x, t, w, v, z, B = this.y();
							if (this.$pullDown.length) {
								u = this._pullDownIsPulled();
								t = this.options.topOffset;
								w = t / 2;
								if (!u && B > w) {
									this._pullDownSetStatePulled(A);
									this.minScrollY(0)
								} else {
									if (u && B <= 0) {
										this._pullDownSetStateReset(A);
										this.minScrollY(-t)
									}
								}
							}
							if (this.$pullUp.length) {
								x = this._pullUpIsPulled();
								v = this.options.bottomOffset;
								z = v / 2;
								if (!x && B < this.maxScrollY() - v - z) {
									this._pullUpSetStatePulled(A);
									this.maxScrollY(this.wrapperH()
											- this.scrollerH()
											+ this.minScrollY())
								} else {
									if (x && B >= this.maxScrollY()) {
										this._pullUpSetStateReset(A);
										this.maxScrollY(this.wrapperH()
												- this.scrollerH()
												+ this.minScrollY() + v)
									}
								}
							}
						},
						_pullOnScrollEnd : function(t) {
							if (this._pullDownIsPulled(t)) {
								this._pullDownSetStateLoading(t);
								this._triggerWidget("onpulldown", t)
							} else {
								if (this._pullUpIsPulled(t)) {
									this._pullUpSetStateLoading(t);
									this._triggerWidget("onpullup", t)
								}
							}
						}
					})
}(jQuery, window, document));
jQuery(document).trigger("iscroll_init");
jQuery(document).bind("pagecreate", function(b) {
	var a = jQuery(b.target).find(":jqmData(iscroll)");
	a.iscrollview()
});