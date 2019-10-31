/*
 FusionCharts JavaScript Library
 Copyright FusionCharts Technologies LLP
 License Information at <http://www.fusioncharts.com/license>
 @version fusioncharts/3.3.1-sr3.21100
*/
FusionCharts(["private", "modules.renderer.js-charts", function() {
	function Da(a) {
		for (var J = {
			left: a.offsetLeft,
			top: a.offsetTop
		}, a = a.offsetParent; a;) J.left += a.offsetLeft, J.top += a.offsetTop, a !== E.body && a !== E.documentElement && (J.left -= a.scrollLeft, J.top -= a.scrollTop), a = a.offsetParent;
		return J
	}
	function ja(a, J) {
		for (var d = [], g = 0, b = a.length; g < b; g++) d[g] = J.call(a[g], a[g], g, a);
		return d
	}
	function sa(a) {
		a = (a || 0) % fa;
		return a < 0 ? fa + a : a
	}
	function Ea(a, J) {
		return a <= W ? a : J <= W ? J : J > a ? 0 : J
	}
	function Ja(a, J, d, g, b) {
		return X((J - d[1] - g.top) / b, a - d[0] - g.left)
	}
	function Fa(a, J, d, g, b, ua, j, r, aa, n) {
		if (typeof a === "object") J = a.y, d = a.r, g = a.innerR, b = a.radiusYFactor, ua = a.depth, j = a.seriesGroup, r = a.renderer, a = a.x;
		if (b < 0 || b >= 1) b = 0.6;
		a = a || 0;
		J = J || 0;
		d = d || 1;
		g = g || 0;
		ua = ua || 0;
		this.renderer = r;
		this.hasOnePoint = aa;
		this.use3DLighting = n;
		this.cx = a;
		this.cy = J;
		this.rx = d;
		this.ry = d * b;
		this.radiusYFactor = b;
		this.isDoughnut = g > 0;
		this.innerRx = g;
		this.innerRy = g * b;
		this.depth = ua;
		this.leftX = a - d;
		this.rightX = a + d;
		this.leftInnerX = a - g;
		this.rightInnerX = a + g;
		this.depthY = J + ua;
		this.topY = J - this.ry;
		this.bottomY = this.depthY + this.ry;
		this.bottomBorderGroup = r.group("bottom-border", j).attr({
			transform: "t0," + ua
		});
		this.outerBackGroup = r.group("outer-back-Side", j);
		this.slicingWallsBackGroup = r.group("slicingWalls-back-Side", j);
		this.innerBackGroup = r.group("inner-back-Side", j);
		this.innerFrontGroup = r.group("inner-front-Side", j);
		this.slicingWallsFrontGroup = r.group("slicingWalls-front-Side", j);
		this.topGroup = r.group("top-Side", j);
		this.moveCmdArr = [c];
		this.lineCmdArr = [k];
		this.closeCmdArr = [h];
		this.centerPoint = [a, J];
		this.leftPoint = [this.leftX, J];
		this.topPoint = [a, this.topY];
		this.rightPoint = [this.rightX, J];
		this.bottomPoint = [a, J + this.ry];
		this.leftDepthPoint = [this.leftX, this.depthY];
		this.rightDepthPoint = [this.rightX, this.depthY];
		this.leftInnerPoint = [this.leftInnerX, J];
		this.rightInnerPoint = [this.rightInnerX, J];
		this.leftInnerDepthPoint = [this.leftInnerX, this.depthY];
		this.rightInnerDepthPoint = [this.rightInnerX, this.depthY];
		this.pointElemStore = [];
		this.slicingWallsArr = [];
		a = [e, this.rx, this.ry, 0, 0, 1, this.rightX, J];
		d = [e, this.rx, this.ry, 0, 0, 1, this.leftX, J];
		g = [e, this.rx, this.ry, 0, 0, 0, this.rightX, this.depthY];
		b = [e, this.rx, this.ry, 0, 0, 0, this.leftX, this.depthY];
		ua = [e, this.innerRx, this.innerRy, 0, 0, 0, this.rightInnerX, J];
		J = [e, this.innerRx, this.innerRy, 0, 0, 0, this.leftInnerX, J];
		j = [e, this.innerRx, this.innerRy, 0, 0, 1, this.rightInnerX, this.depthY];
		r = [e, this.innerRx, this.innerRy, 0, 0, 1, this.leftInnerX, this.depthY];
		this.isDoughnut ? (this.topBorderPath = this.moveCmdArr.concat(this.leftPoint, a, d, this.moveCmdArr, this.leftInnerPoint, ua, J), this.topPath = this.moveCmdArr.concat(this.leftPoint, a, d, this.lineCmdArr, this.leftInnerPoint, ua, J, this.closeCmdArr), this.innerFrontPath = this.moveCmdArr.concat(this.leftInnerPoint, ua, this.lineCmdArr, this.rightInnerDepthPoint, r, this.closeCmdArr), this.innerBackPath = this.moveCmdArr.concat(this.rightInnerPoint, J, this.lineCmdArr, this.leftInnerDepthPoint, j, this.closeCmdArr)) : this.topBorderPath = this.topPath = this.moveCmdArr.concat(this.leftPoint, a, d, this.closeCmdArr);
		this.outerBackPath = this.moveCmdArr.concat(this.leftPoint, a, this.lineCmdArr, this.rightDepthPoint, b, this.closeCmdArr);
		this.outerFrontPath = this.moveCmdArr.concat(this.rightPoint, d, this.lineCmdArr, this.leftDepthPoint, g, this.closeCmdArr);
		this.clipPathforOuter = [c, this.leftX, this.topY, k, this.rightX, this.topY, this.rightX, this.bottomY, this.leftX, this.bottomY, h];
		this.clipPathforInner = [c, this.leftInnerX, this.topY, k, this.rightInnerX, this.topY, this.rightInnerX, this.bottomY, this.leftInnerX, this.bottomY, h];
		this.clipPathforNoClip = [c, this.leftInnerX, this.topY, k, this.leftInnerX, this.bottomY, h]
	}
	var ma = this,
		t = ma.hcLib,
		ga = t.Raphael,
		E = window.document,
		B = t.BLANKSTRING,
		Ka = t.createTrendLine,
		i = t.pluck,
		va = t.getValidValue,
		f = t.pluckNumber,
		U = t.defaultPaletteOptions,
		ka = t.getFirstValue,
		Oa = t.getDefinedColor,
		wa = t.parseUnsafeString,
		O = t.FC_CONFIG_STRING,
		xa = t.extend2,
		za = t.getDashStyle,
		ba = t.toRaphaelColor,
		Qa = t.toPrecision,
		Ga = t.stubFN,
		Z = t.hasSVG,
		Aa = t.isIE,
		qa = t.each,
		La = t.hasTouch ? 10 : 3,
		la = "rgba(192,192,192," + (Aa ? 0.002 : 1.0E-6) + ")",
		b = document.documentMode === 8 ? "visible" : "",
		c = "M",
		k = "L",
		e = "A",
		h = "Z",
		H = Math,
		P = H.sin,
		w = H.cos,
		X = H.atan2,
		A = H.round,
		ha = H.min,
		Y = H.max,
		M = H.abs,
		u = H.PI,
		ta = H.ceil,
		ia = H.floor,
		R = H.sqrt,
		T = u / 180,
		W = Math.PI,
		S = W / 2,
		fa = 2 * W,
		Ua = W + S,
		Ra = t.graphics.getColumnColor,
		V = t.getFirstColor,
		Ca = t.setLineHeight,
		Ha = t.pluckFontSize,
		Ma = t.getFirstAlpha,
		ca = t.graphics.getDarkColor,
		ea = t.graphics.getLightColor,
		na = t.graphics.convertColor,
		Na = t.COLOR_TRANSPARENT,
		la = "rgba(192,192,192," + (Aa ? 0.002 : 1.0E-6) + ")",
		Sa = t.POSITION_CENTER,
		Wa = t.POSITION_TOP,
		Va = t.POSITION_BOTTOM,
		Xa = t.POSITION_RIGHT,
		Ya = t.POSITION_LEFT,
		o = t.chartAPI,
		Za = t.titleSpaceManager,
		$a = t.placeLegendBlockBottom,
		ab = t.placeLegendBlockRight,
		bb = t.graphics.mapSymbolName,
		Aa = o.singleseries,
		K = t.COMMASTRING,
		ya = t.ZEROSTRING,
		Ia = t.ONESTRING,
		Ba = t.HUNDREDSTRING,
		Pa = t.PXSTRING,
		cb = t.COMMASPACE,
		ra = !/fusioncharts\.com$/i.test(location.hostname);
	o("column2d", {
		standaloneInit: !0,
		friendlyName: "Column Chart",
		creditLabel: ra,
		rendererId: "cartesian"
	}, o.column2dbase);
	o("column3d", {
		friendlyName: "3D Column Chart",
		defaultSeriesType: "column3d",
		defaultPlotShadow: 1,
		is3D: !0,
		defaultZeroPlaneHighlighted: !1
	}, o.column2d);
	o("bar2d", {
		friendlyName: "Bar Chart",
		isBar: !0,
		defaultSeriesType: "bar",
		spaceManager: o.barbase
	}, o.column2d);
	o("bar3d", {
		friendlyName: "3D Bar Chart",
		defaultSeriesType: "bar3d",
		defaultPlotShadow: 1,
		is3D: !0,
		defaultZeroPlaneHighlighted: !1
	}, o.bar2d);
	o("line", {
		friendlyName: "Line Chart",
		standaloneInit: !0,
		creditLabel: ra,
		rendererId: "cartesian"
	}, o.linebase);
	o("area2d", {
		friendlyName: "Area Chart",
		standaloneInit: !0,
		creditLabel: ra,
		rendererId: "cartesian"
	}, o.area2dbase);
	o("pie2d", {
		friendlyName: "Pie Chart",
		standaloneInit: !0,
		defaultSeriesType: "pie",
		defaultPlotShadow: 1,
		sliceOnLegendClick: !0,
		rendererId: "pie",
		point: function(a, J, d, g, b) {
			var c, j, r, e = b[O],
				n = e.is3d,
				m, p, l, k, h = 0,
				y = 0,
				o = [];
			r = f(g.plotborderthickness);
			var C = f(r, n ? 0.1 : 1),
				q = (m = f(g.use3dlighting, 1)) ? f(g.radius3d, g["3dradius"], 90) : 100;
			p = f(g.showzeropies, 1);
			var x = f(g.showpercentintooltip, 1),
				Ta = f(g.showlabels, 1),
				H = f(g.showvalues, 1),
				w = f(g.showpercentvalues, g.showpercentagevalues, 0),
				F = i(g.tooltipsepchar, g.hovercapsepchar, cb),
				v = i(g.labelsepchar, F),
				s = i(g.plotbordercolor, g.piebordercolor),
				z = b[O].numberFormatter;
			l = d.length;
			var A, Q, D = f(g.plotborderdashed, 0),
				L = f(g.plotborderdashlen, 5),
				G = f(g.plotborderdashgap, 4);
			q > 100 && (q = 100);
			q < 0 && (q = 0);
			if (f(g.showlegend, 0)) b.legend.enabled = !0, b.legend.reversed = !Boolean(f(g.reverselegend, 0)), J.showInLegend = !0;
			for (a = 0; a < l; a += 1) j = d[a], c = z.getCleanValue(j.value, !0), c === null || !p && c === 0 || (o.push(j), h += c);
			h === 0 && (o = []);
			J.enableRotation = o.length > 1 ? f(g.enablerotation, 1) : 0;
			J.alphaAnimation = f(g.alphaanimation, 1);
			J.is3D = n;
			J.use3DLighting = m;
			J.pieYScale = f(g.pieyscale, 40);
			if (J.pieYScale < 1) J.pieYScale = 1;
			if (J.pieYScale >= 100) J.pieYScale = 80;
			J.pieYScale /= 100;
			J.pieSliceDepth = f(g.pieslicedepth, 15);
			if (J.pieSliceDepth < 1) J.pieSliceDepth = 1;
			J.managedPieSliceDepth = J.pieSliceDepth;
			if (n && g.showplotborder != Ia && !r) J.showBorderEffect = 1;
			for (a = o.length - 1; a >= 0; a -= 1) {
				j = o[a];
				c = z.getCleanValue(j.value, !0);
				d = wa(i(j.label, j.name, B));
				m = i(j.color, b.colors[a % b.colors.length]);
				p = i(j.alpha, g.plotfillalpha);
				l = i(j.bordercolor, s);
				k = i(j.borderalpha, g.plotborderalpha, g.pieborderalpha);
				if (n && (l || k !== void 0)) J.showBorderEffect = 0;
				l = i(l, ea(m, n ? 90 : 25)).split(K)[0];
				k = g.showplotborder == ya ? ya : i(k, p, "80");
				p = i(p, Ba);
				r = {
					opacity: Math.max(p, k) / 100
				};
				if (A = Boolean(f(j.issliced, g.issliced, 0))) e.preSliced = A;
				Q = f(j.dashed, D) ? za(i(j.dashlen, L), i(j.dashgap, G), C) : void 0;
				J.data.push({
					showInLegend: d !== B,
					y: c,
					name: d,
					shadow: r,
					toolText: wa(va(j.tooltext)),
					color: this.getPointColor(m, p, q),
					_3dAlpha: p,
					borderColor: na(l, k),
					borderWidth: C,
					link: va(j.link),
					sliced: A,
					dashStyle: Q,
					doNotSlice: i(g.enableslicing, Ia) != Ia
				});
				p = z.percentValue(c / h * 100);
				l = z.dataLabels(c) || B;
				r = x === 1 ? p : l;
				c = f(j.showlabel, Ta) === 1 ? d : B;
				p = (m = f(j.showvalue, H)) === 1 ? w === 1 ? p : l : B;
				j = va(wa(j.displayvalue));
				p = j !== void 0 && m ? j : p !== B && c !== B ? c + v + p : i(c, p);
				d = d != B ? d + F + r : r;
				j = J.data[y];
				j.displayValue = p;
				j.toolText = i(j.toolText, d);
				y += 1
			}
			J.valueTotal = h;
			b.legend.enabled = g.showlegend == Ia ? !0 : !1;
			J.startAngle = f(g.startingangle, 0);
			b.chart.startingAngle = i(o.length > 1 ? g.startingangle : 0, 0);
			return J
		},
		getPointColor: function(a, J, d) {
			var g, b, a = V(a),
				J = Ma(J);
			d < 100 && Z ? (g = Math.floor((100 - 0.35 * d) * 85) / 100, g = ca(a, g), b = Math.floor((100 + d) * 50) / 100, a = ea(a, b), J = {
				FCcolor: {
					color: a + K + g,
					alpha: J + K + J,
					ratio: d + ",100",
					radialGradient: !0,
					gradientUnits: "userSpaceOnUse"
				}
			}) : J = {
				FCcolor: {
					color: a + K + a,
					alpha: J + K + J,
					ratio: "0,100"
				}
			};
			return J
		},
		configureAxis: function(a) {
			var J = 0,
				d = a[O],
				g;
			a.plotOptions.series.dataLabels.style = a.xAxis.labels.style;
			a.plotOptions.series.dataLabels.color = a.xAxis.labels.style.color;
			delete d.x;
			delete d[0];
			delete d[1];
			a.chart.plotBorderColor = a.chart.plotBackgroundColor = Na;
			d = d.pieDATALabels = [];
			if (a.series.length === 1 && (g = a.series[0].data) && (J = a.series[0].data.length) > 0 && a.plotOptions.series.dataLabels.enabled) for (; J--;) g[J] && va(g[J].displayValue) !== void 0 && d.push(g[J].displayValue)
		},
		spaceManager: function(a, J, d, g) {
			var b = a[O],
				c = b.is3d,
				j = this.name,
				r = b.smartLabel,
				e = f(b.pieDATALabels && b.pieDATALabels.length, 0),
				n = 0,
				m = J.chart,
				p = f(m.managelabeloverflow, 0),
				l = !b.preSliced && m.enableslicing == ya && (m.showlegend != Ia || m.interactivelegend == ya) ? 0 : f(m.slicingdistance, 20),
				k = f(m.pieradius, 0),
				h = f(m.enablesmartlabels, m.enablesmartlabel, 1),
				y = h ? f(m.skipoverlaplabels, m.skipoverlaplabel, 1) : 0,
				o = f(m.issmartlineslanted, 1),
				C = f(m.labeldistance, m.nametbdistance, 5),
				q = f(m.smartlabelclearance, 5);
			d -= a.chart.marginRight + a.chart.marginLeft;
			var x = g - (a.chart.marginTop + a.chart.marginBottom),
				g = Math.min(x, d),
				Ta = i(m.smartlinecolor, U.plotFillColor[a.chart.paletteIndex]),
				H = f(m.smartlinealpha, 100),
				w = f(m.smartlinethickness, 0.7),
				F = a.plotOptions.series.dataLabels,
				v = F.style,
				s = f(parseInt(v.lineHeight, 10), 12),
				z = a.series[0] || {},
				A = z.pieYScale,
				Q = z.pieSliceDepth,
				g = k === 0 ? g * 0.15 : k,
				D = 0,
				D = 2 * g;
			F.connectorWidth = w;
			F.connectorPadding = f(m.connectorpadding, 5);
			F.connectorColor = na(Ta, H);
			x -= Za(a, J, d, D < x ? x - D : x / 2);
			m.showlegend == Ia && (i(m.legendposition, Va).toLowerCase() != Xa ? x -= $a(a, J, d, x / 2, !0) : d -= ab(a, J, d / 3, x, !0));
			r.setStyle(v);
			if (e !== 1) for (; e--;) J = r.getOriSize(b.pieDATALabels[e]), n = Math.max(n, J.width);
			h && (C = q + l);
			k === 0 && (c ? (x -= Q, D = Math.min(d / 2 - n, (x / 2 - s) / A) - C) : D = Math.min(d / 2 - n, x / 2 - s) - C, D >= g ? g = D : C = Math.max(C - (g - D), l));
			if (c && (e = x - 2 * (g * A + s), Q > e)) z.managedPieSliceDepth = Q - e;
			a.plotOptions.pie3d.slicedOffset = a.plotOptions.pie.slicedOffset = l;
			a.plotOptions.pie3d.size = a.plotOptions.pie.size = 2 * g;
			a.plotOptions.series.dataLabels.distance = C;
			a.plotOptions.series.dataLabels.isSmartLineSlanted = o;
			a.plotOptions.series.dataLabels.enableSmartLabels = h;
			a.plotOptions.series.dataLabels.skipOverlapLabels = y;
			a.plotOptions.series.dataLabels.manageLabelOverflow = p;
			if (j === "doughnut2d" || j === "doughnut3d") if (j = f(m.doughnutradius, 0), e = f(m.use3dlighting, 1) ? f(m.radius3d, m["3dradius"], 50) : 100, e > 100 && (e = 100), e < 0 && (e = 0), m = j === 0 || j >= g ? g / 2 : j, a.plotOptions.pie3d.innerSize = a.plotOptions.pie.innerSize = 2 * m, e > 0 && Z && (m = parseInt(m / g * 100, 10), j = (100 - m) / 2, e = parseInt(j * e / 100, 10), m = m + K + e + K + 2 * (j - e) + K + e, a.series[0] && a.series[0].data)) {
				p = a.series[0].data;
				a = 0;
				for (e = p.length; a < e; a += 1) if (j = p[a], j.color.FCcolor) j.color.FCcolor.ratio = m
			}
		},
		creditLabel: ra,
		eiMethods: {
			sliceDataItem: function(a) {
				var b = this.jsVars.hcObj,
					d, g, c;
				if (b && b.datasets && (d = b.datasets[0]) && (g = d.data) && (c = g.length) && g[a = c - a - 1] && g[a].plot) return b.plotGraphicClick.call(g[a].plot)
			}
		}
	}, Aa);
	o.pie2d.eiMethods.togglePieSlice = o.pie2d.eiMethods.sliceDataItem;
	o.pie2d.eiMethods.enableSlicingMovement = o.pie2d.eiMethods.enablelink = function() {
		ma.raiseWarning(this, "1301081430", "run", "JSRenderer~enablelink()", "Method deprecated.")
	};
	o("pie3d", {
		friendlyName: "3D Pie Chart",
		defaultSeriesType: "pie3d",
		rendererId: "pie3d",
		creditLabel: ra,
		getPointColor: function(a) {
			return a
		},
		defaultPlotShadow: 0
	}, o.pie2d);
	o("doughnut2d", {
		friendlyName: "Doughnut Chart",
		getPointColor: function(a, b, d) {
			var g, a = V(a),
				b = Ma(b);
			d < 100 && Z ? (g = ca(a, ia((85 - 0.2 * (100 - d)) * 100) / 100), a = ea(a, ia((100 - 0.5 * d) * 100) / 100), b = {
				FCcolor: {
					color: g + "," + a + "," + a + "," + g,
					alpha: b + "," + b + "," + b + "," + b,
					radialGradient: !0,
					gradientUnits: "userSpaceOnUse",
					r: d
				}
			}) : b = {
				FCcolor: {
					color: a + "," + a,
					alpha: b + "," + b,
					ratio: "0,100"
				}
			};
			return b
		}
	}, o.pie2d);
	o("doughnut3d", {
		friendlyName: "3D Doughnut Chart",
		defaultSeriesType: "pie3d",
		rendererId: "pie3d",
		getPointColor: o.pie3d,
		defaultPlotShadow: 0
	}, o.doughnut2d);
	o("pareto2d", {
		friendlyName: "Pareto Chart",
		standaloneInit: !0,
		point: function(a, b, d, g, c) {
			var e, j, r, aa, n, m, p, l, k, h, y, o, C, q, x, a = d.length,
				H = 0;
			m = {};
			C = c.chart.paletteIndex;
			var w = /3d$/.test(c.chart.defaultSeriesType),
				A = this.isBar,
				F = i(360 - g.plotfillangle, 90),
				v = f(g.plotborderthickness, 1),
				s = c.chart.useRoundEdges,
				z = i(g.tooltipsepchar, ", "),
				P = i(g.plotbordercolor, U.plotBorderColor[C]).split(K)[0],
				Q = g.showplotborder == ya ? ya : i(g.plotborderalpha, g.plotfillalpha, Ba),
				D = c.xAxis,
				L = f(g.showcumulativeline, 1),
				G = c[O],
				I = G.axisGridManager,
				u = G.x,
				ha = g.showtooltip != ya,
				N = [],
				X = [],
				Y = f(g.use3dlighting, 1),
				M = c[O].numberFormatter,
				t = f(g.showlinevalues, g.showvalues),
				W = f(g.plotborderdashed, 0),
				E = f(g.plotborderdashlen, 5),
				S = f(g.plotborderdashgap, 4),
				Q = w ? g.showplotborder ? Q : ya : Q,
				P = w ? i(g.plotbordercolor, "#FFFFFF") : P;
			for (o = j = 0; j < a; j += 1) if (y = d[j], d[j].vline) I.addVline(D, y, o, c);
			else if (e = M.getCleanValue(y.value, !0), e !== null) y.value = e, N.push(y), o += 1;
			a = N.length;
			N.sort(function(a, d) {
				return d.value - a.value
			});
			if (L) p = f(g.linedashed, 0), q = V(i(g.linecolor, U.plotBorderColor[C])), j = i(g.linealpha, 100), l = f(g.linedashlen, 5), k = f(g.linedashgap, 4), m = f(g.linethickness, 2), h = {
				opacity: j / 100
			}, x = f(g.drawanchors, g.showanchors), x === void 0 && (x = j != ya), r = f(g.anchorborderthickness, 1), n = f(g.anchorsides, 0), aa = f(g.anchorradius, 3), o = V(i(g.anchorbordercolor, q)), e = V(i(g.anchorbgcolor, U.anchorBgColor[C])), d = Ma(i(g.anchoralpha, Ba)), y = Ma(i(g.anchorbgalpha, d)) * d / 100, p = p ? za(l, k, m) : void 0, m = {
				yAxis: 1,
				data: [],
				type: "line",
				color: {
					FCcolor: {
						color: q,
						alpha: j
					}
				},
				lineWidth: m,
				marker: {
					enabled: x,
					fillColor: {
						FCcolor: {
							color: e,
							alpha: y
						}
					},
					lineColor: {
						FCcolor: {
							color: o,
							alpha: d
						}
					},
					lineWidth: r,
					radius: aa,
					symbol: bb(n),
					startAngle: i(g.anchorstartangle, 90)
				}
			};
			else {
				if (g.showsecondarylimits !== "1") g.showsecondarylimits = "0";
				if (g.showdivlinesecondaryvalue !== "1") g.showdivlinesecondaryvalue = "0"
			}
			for (j = 0; j < a; j += 1) y = N[j], e = f(y.showlabel, g.showlabels, 1), d = wa(!e ? B : ka(y.label, y.name)), I.addXaxisCat(D, j, j, d), H += e = y.value, r = i(y.color, c.colors[j % c.colors.length]) + K + (f(g.useplotgradientcolor, 1) ? Oa(g.plotgradientcolor, U.plotGradientColor[C]) : B), aa = i(y.alpha, g.plotfillalpha, Ba), n = i(y.ratio, g.plotfillratio), o = {
				opacity: aa / 100
			}, q = i(y.alpha, Q) + B, r = Ra(r, aa, n, F, s, P, q, A, w), b.data.push(xa(this.getPointStub(y, e, d, c), {
				y: e,
				shadow: o,
				color: r[0],
				borderColor: r[1],
				borderWidth: v,
				use3DLighting: Y,
				dashStyle: f(y.dashed, W) == 1 ? za(E, S, v) : "",
				tooltipConstraint: this.tooltipConstraint
			})), this.pointValueWatcher(c, e), L && X.push({
				value: H,
				dataLabel: d,
				tooltext: va(y.tooltext)
			});
			u.catCount = a;
			G[1] || (G[1] = {});
			G[1].stacking100Percent = !0;
			if (L && H > 0) {
				j = 0;
				for (a = X.length; j < a; j += 1) y = X[j], c = b.data[j], e = y.value / H * 100, C = M.percentValue(e), g = c.displayValue !== B ? C : B, t == 1 && (g = C), t == 0 && (g = B), d = y.dataLabel, C = ha ? y.tooltext !== void 0 ? y.tooltext : (d !== B ? d + z : B) + C : B, m.data.push({
					shadow: h,
					color: m.color,
					marker: m.marker,
					y: e,
					toolText: C,
					displayValue: g,
					link: c.link,
					dashStyle: p
				});
				return [b, m]
			} else return b
		},
		defaultSeriesType: "column",
		isDual: !0,
		creditLabel: ra,
		rendererId: "cartesian"
	}, Aa);
	o("pareto3d", {
		friendlyName: "3D Pareto Chart",
		defaultSeriesType: "column3d",
		defaultPlotShadow: 1,
		is3D: !0
	}, o.pareto2d);
	o("mscolumn2d", {
		friendlyName: "Multi-series Column Chart",
		standaloneInit: !0,
		creditLabel: ra,
		rendererId: "cartesian"
	}, o.mscolumn2dbase);
	o("mscolumn3d", {
		friendlyName: "Multi-series 3D Column Chart",
		defaultSeriesType: "column3d",
		defaultPlotShadow: 1,
		is3D: !0,
		defaultZeroPlaneHighlighted: !1
	}, o.mscolumn2d);
	o("msbar2d", {
		friendlyName: "Multi-series Bar Chart",
		isBar: !0,
		defaultSeriesType: "bar",
		spaceManager: o.barbase
	}, o.mscolumn2d);
	o("msbar3d", {
		friendlyName: "Multi-series 3D Bar Chart",
		defaultSeriesType: "bar3d",
		defaultPlotShadow: 1,
		is3D: !0,
		defaultZeroPlaneHighlighted: !1
	}, o.msbar2d);
	o("msline", {
		friendlyName: "Multi-series Line Chart",
		standaloneInit: !0,
		creditLabel: ra,
		rendererId: "cartesian"
	}, o.mslinebase);
	o("msarea", {
		friendlyName: "Multi-series Area Chart",
		standaloneInit: !0,
		creditLabel: ra,
		rendererId: "cartesian"
	}, o.msareabase);
	o("stackedcolumn2d", {
		friendlyName: "Stacked Column Chart",
		isStacked: !0
	}, o.mscolumn2d);
	o("stackedcolumn3d", {
		friendlyName: "3D Stacked Column Chart",
		isStacked: !0
	}, o.mscolumn3d);
	o("stackedbar2d", {
		friendlyName: "Stacked Bar Chart",
		isStacked: !0
	}, o.msbar2d);
	o("stackedbar3d", {
		friendlyName: "3D Stacked Bar Chart",
		isStacked: !0
	}, o.msbar3d);
	o("stackedarea2d", {
		friendlyName: "Stacked Area Chart",
		isStacked: !0,
		areaAlpha: 100,
		showSum: 0
	}, o.msarea);
	o("marimekko", {
		friendlyName: "Marimekko Chart",
		isValueAbs: !0,
		distributedColumns: !0,
		isStacked: !0,
		xAxisMinMaxSetter: Ga,
		postSeriesAddition: function(a, b) {
			var d = a[O],
				g = 0,
				c = a.xAxis,
				e = 100 / d.marimekkoTotal,
				j = [],
				r = a.series,
				aa = 0,
				n = f(b.chart.plotborderthickness, 1),
				m = a.chart.rotateValues,
				p = f(b.chart.rotatexaxispercentvalues, 0),
				l = n * -0.5 - (n % 2 + (p ? 0 : 4)),
				k = p ? 3 : 0,
				h = m ? 270 : 0,
				y = xa({}, a.plotOptions.series.dataLabels.style),
				o = parseInt(y.fontSize, 10),
				C = d[0],
				q = C.stacking100Percent,
				x = !q,
				H = d.inCanvasStyle,
				w = this.numberFormatter,
				i = b.categories && b.categories[0] && b.categories[0].category || [],
				F = 0,
				v = [],
				s, z, P, Q, D, L, G, I, u, n = [];
			d.isXYPlot = !0;
			d.distributedColumns = !0;
			c.min = 0;
			c.max = 100;
			c.labels.enabled = !1;
			c.gridLineWidth = 0;
			c.alternateGridColor = Na;
			s = C.stack;
			b.chart.interactivelegend = "0";
			C = 0;
			for (z = a.xAxis.plotLines.length; C < z; C += 1) if (P = c.plotLines[C], P.isGrid) P.isCat = !0, j[P.value] = P, P._hideLabel = !0;
			for (C = z = 0; C < i.length; C += 1) i[C].vline || (F += v[z] = w.getCleanValue(i[C].widthpercent || 0), z += 1);
			i = s.floatedcolumn && s.floatedcolumn[0] || [];
			if (F === 100 && (i && i.length) !== z) for (; z--;) i[z] || (i[z] = {
				p: null
			});
			F = A(F);
			if (i) {
				D = 0;
				for (z = i.length; D < z;) {
					u = i[D];
					g += Q = u && u.p || 0;
					G = F === 100 ? v[D] : Q * e;
					L = aa + G / 2;
					I = aa + G;
					n.push(I);
					for (C = 0; C < r.length; C += 1) if (s = a.series[C].data[D], s._FCX = aa, s._FCW = G, q) {
						if (s.y || s.y === 0) {
							P = s.y / Q * 100;
							s.y = P;
							if (s.showPercentValues) s.displayValue = this.numberFormatter.percentValue(P);
							if (s.showPercentInToolTip) s.toolText = s.toolText + parseInt(P * 100, 10) / 100 + "%"
						}
						if (s.previousY || s.previousY === 0) s.previousY = s.previousY / Q * 100
					}
					d.showStackTotal && a.xAxis.plotLines.push({
						value: L,
						width: 0,
						isVline: x,
						isTrend: !x,
						_isStackSum: 1,
						zIndex: 4,
						label: {
							align: Sa,
							textAlign: h,
							rotation: m ? 270 : 0,
							style: y,
							verticalAlign: Wa,
							offsetScale: x ? Q < 0 ? u.n : u.p : void 0,
							offsetScaleIndex: 0,
							y: Q < 0 ? m === 270 ? 4 : o : -4,
							x: 0,
							text: w.yAxis(Qa(Q, 10))
						}
					});
					if (j[D]) j[D].value = L, j[D]._weight = G, j[D]._hideLabel = !1;
					D += 1;
					d.showXAxisPercentValues && D < z && a.xAxis.plotLines.push({
						value: I,
						width: 0,
						isVine: !0,
						label: {
							align: Sa,
							textAlign: p ? Ya : Sa,
							rotation: p ? 270 : 0,
							backgroundColor: "#ffffff",
							backgroundOpacity: 1,
							borderWidth: "1px",
							borderType: "solid",
							borderColor: H.color,
							style: {
								color: H.color,
								fontSize: H.fontSize,
								fontFamily: H.fontFamily,
								lineHeight: H.lineHeight
							},
							verticalAlign: Va,
							y: l,
							x: k,
							text: this.numberFormatter.percentValue(I)
						},
						zIndex: 5
					});
					aa = I
				}
			}
			D = 0;
			for (z = j.length; D < z; D += 1) if (j[D] && j[D]._hideLabel) j[D].value = null;
			C = 0;
			for (z = a.xAxis.plotLines.length; C < z; C += 1) if (P = c.plotLines[C], P.isVline && !P._isStackSum && (d = P.value)) d = A(d - 0.5), P.value = n[d]
		},
		defaultSeriesType: "floatedcolumn"
	}, o.stackedcolumn2d);
	o("msstackedcolumn2d", {
		friendlyName: "Multi-series Stacked Column Chart",
		series: function(a, b, d) {
			var g, c, e, j, r = b[O],
				aa = 0,
				n, m;
			n = [];
			var p;
			b.legend.enabled = Boolean(f(a.chart.showlegend, 1));
			if (a.dataset && a.dataset.length > 0) {
				this.categoryAdder(a, b);
				g = 0;
				for (c = a.dataset.length; g < c; g += 1) if (p = a.dataset[g].dataset) {
					e = 0;
					for (j = p.length; e < j; e += 1, aa += 1) n = {
						visible: !! f(p.visible, 1),
						data: [],
						numColumns: c,
						columnPosition: g
					}, m = Math.min(r.oriCatTmp.length, p[e].data && p[e].data.length), n = this.point(d, n, p[e], a.chart, b, m, aa, g), b.series.push(n)
				}
				if (this.isDual && a.lineset && a.lineset.length > 0) {
					e = 0;
					for (j = a.lineset.length; e < j; e += 1, aa += 1) n = {
						visible: !! f(a.lineset[e].visible, 1),
						data: [],
						yAxis: 1,
						type: "line"
					}, d = a.lineset[e], m = Math.min(r.oriCatTmp.length, d.data && d.data.length), b.series.push(o.msline.point.call(this, "msline", n, d, a.chart, b, m, aa))
				}
				this.configureAxis(b, a);
				a.trendlines && Ka(a.trendlines, b.yAxis, b[O], this.isDual, this.isBar)
			}
		},
		postSpaceManager: function(a, b, d) {
			var l;
			var g = a[O],
				c, e, j;
			if (this.isStacked && g.showStackTotal && (c = a.chart, l = (b = a.xAxis) && b.plotLines, a = l, c = d - c.marginLeft - c.marginRight, d = g.plotSpacePercent, g = g[0].stack, g = g.column && g.column.length, b = c / (b.max - b.min), b * ((1 - 2 * d) / g) > 50 && d == 0.1)) {
				b = 50 / b;
				d = a && a.length;
				g = -((g - 1) / 2) * b;
				for (j = 0; j < d; j += 1) if (e = a[j], e._isStackSum) c = e._catPosition + (g + b * e._stackIndex), e.value = c
			}
		}
	}, o.stackedcolumn2d);
	o("mscombi2d", {
		friendlyName: "Multi-series Combination Chart",
		standaloneInit: !0,
		creditLabel: ra,
		rendererId: "cartesian"
	}, o.mscombibase);
	o("mscombi3d", {
		friendlyName: "Multi-series 3D Combination Chart",
		series: o.mscombi2d.series,
		eiMethods: function(a) {
			var b = {};
			qa(a.split(","), function(a) {
				b[a] = function() {
					ma.raiseWarning(this, "1301081430", "run", "JSRenderer~" + a + "()", "Method not applicable.")
				}
			});
			return b
		}("view2D,view3D,resetView,rotateView,getViewAngles,fitToStage")
	}, o.mscolumn3d);
	o("mscolumnline3d", {
		friendlyName: "Multi-series Column and Line Chart"
	}, o.mscombi3d);
	o("stackedcolumn2dline", {
		friendlyName: "Stacked Column and Line Chart",
		isStacked: !0,
		stack100percent: 0
	}, o.mscombi2d);
	o("stackedcolumn3dline", {
		friendlyName: "Stacked 3D Column and Line Chart",
		isStacked: !0,
		stack100percent: 0
	}, o.mscombi3d);
	o("mscombidy2d", {
		friendlyName: "Multi-series Dual Y-Axis Combination Chart",
		isDual: !0,
		secondarySeriesType: void 0
	}, o.mscombi2d);
	o("mscolumn3dlinedy", {
		friendlyName: "Multi-series 3D Column and Line Chart",
		isDual: !0,
		secondarySeriesType: "line"
	}, o.mscolumnline3d);
	o("stackedcolumn3dlinedy", {
		friendlyName: "Stacked 3D Column and Line Chart",
		isDual: !0,
		secondarySeriesType: "line"
	}, o.stackedcolumn3dline);
	o("msstackedcolumn2dlinedy", {
		friendlyName: "Multi-series Dual Y-Axis Stacked Column and Line Chart",
		isDual: !0,
		stack100percent: 0,
		secondarySeriesType: "line"
	}, o.msstackedcolumn2d);
	o("scrollcolumn2d", {
		friendlyName: "Scrollable Multi-series Column Chart",
		postSeriesAddition: o.scrollbase.postSeriesAddition,
		tooltipConstraint: "plot",
		canvasborderthickness: 1,
		avgScrollPointWidth: 40
	}, o.mscolumn2d);
	o("scrollline2d", {
		friendlyName: "Scrollable Multi-series Line Chart",
		postSeriesAddition: o.scrollbase.postSeriesAddition,
		tooltipConstraint: "plot",
		canvasborderthickness: 1,
		avgScrollPointWidth: 75
	}, o.msline);
	o("scrollarea2d", {
		friendlyName: "Scrollable Multi-series Area Chart",
		postSeriesAddition: o.scrollbase.postSeriesAddition,
		tooltipConstraint: "plot",
		canvasborderthickness: 1,
		avgScrollPointWidth: 75
	}, o.msarea);
	o("scrollstackedcolumn2d", {
		friendlyName: "Scrollable Stacked Column Chart",
		postSeriesAddition: function(a, b, d, g) {
			o.base.postSeriesAddition.call(this, a, b, d, g);
			o.scrollbase.postSeriesAddition.call(this, a, b, d, g)
		},
		canvasborderthickness: 1,
		tooltipConstraint: "plot",
		avgScrollPointWidth: 75
	}, o.stackedcolumn2d);
	o("scrollcombi2d", {
		friendlyName: "Scrollable Combination Chart",
		postSeriesAddition: o.scrollbase.postSeriesAddition,
		tooltipConstraint: "plot",
		canvasborderthickness: 1,
		avgScrollPointWidth: 40
	}, o.mscombi2d);
	o("scrollcombidy2d", {
		friendlyName: "Scrollable Dual Y-Axis Combination Chart",
		postSeriesAddition: o.scrollbase.postSeriesAddition,
		tooltipConstraint: "plot",
		canvasborderthickness: 1,
		avgScrollPointWidth: 40
	}, o.mscombidy2d);
	o("scatter", {
		friendlyName: "Scatter Chart",
		standaloneInit: !0,
		defaultSeriesType: "scatter",
		defaultZeroPlaneHighlighted: !1,
		creditLabel: ra
	}, o.scatterbase);
	o("bubble", {
		friendlyName: "Bubble Chart",
		standaloneInit: !0,
		standaloneInut: !0,
		defaultSeriesType: "bubble",
		rendererId: "bubble",
		point: function(a, b, d, g, c, e, j) {
			if (d.data) {
				var r, aa, n, m, p, l, k, h, y, H, C = !1,
					q, x, a = o[a],
					e = d.data,
					w = e.length,
					A = f(d.showvalues, c[O].showValues);
				n = f(g.bubblescale, 1);
				var P = i(g.negativecolor, "FF0000"),
					F = c.plotOptions.bubble,
					v = this.numberFormatter,
					s = f(d.showregressionline, g.showregressionline, 0);
				F.bubbleScale = n;
				b.name = va(d.seriesname);
				if (f(d.includeinlegend) === 0 || b.name === void 0) b.showInLegend = !1;
				n = Boolean(f(d.drawanchors, d.showanchors, g.drawanchors, 1));
				k = i(d.plotfillalpha, d.bubblefillalpha, g.plotfillalpha, Ba);
				h = f(d.showplotborder, g.showplotborder, 1);
				y = V(i(d.plotbordercolor, g.plotbordercolor, "666666"));
				r = i(d.plotborderthickness, g.plotborderthickness, 1);
				H = i(d.plotborderalpha, g.plotborderalpha, "95");
				h = h == 1 ? r : 0;
				j = i(d.color, d.plotfillcolor, g.plotfillcolor, c.colors[j % c.colors.length]);
				b.marker = {
					enabled: n,
					fillColor: this.getPointColor(j, Ba),
					lineColor: {
						FCcolor: {
							color: y,
							alpha: H
						}
					},
					lineWidth: h,
					symbol: "circle"
				};
				if (s) {
					b.events = {
						hide: this.hideRLine,
						show: this.showRLine
					};
					var z = {
						sumX: 0,
						sumY: 0,
						sumXY: 0,
						sumXsqure: 0,
						sumYsqure: 0,
						xValues: [],
						yValues: []
					},
						u = f(d.showyonx, g.showyonx, 1),
						Q = V(i(d.regressionlinecolor, g.regressionlinecolor, j)),
						D = f(d.regressionlinethickness, g.regressionlinethickness, 1);
					r = Ma(f(d.regressionlinealpha, g.regressionlinealpha, 100));
					Q = na(Q, r)
				}
				for (aa = 0; aa < w; aa += 1) if (m = e[aa]) if (r = v.getCleanValue(m.y), q = v.getCleanValue(m.x), x = v.getCleanValue(m.z, !0), r === null) b.data.push({
					y: null,
					x: q
				});
				else {
					C = !0;
					p = V(i(m.color, m.z < 0 ? P : j));
					l = i(m.alpha, k);
					m = a.getPointStub(m, r, q, c, d, A);
					p = f(g.use3dlighting) === 0 ? p : a.getPointColor(p, l);
					if (x !== null) F.zMax = F.zMax > x ? F.zMax : x, F.zMin = F.zMin < x ? F.zMin : x;
					b.data.push({
						y: r,
						x: q,
						z: x,
						displayValue: m.displayValue,
						toolText: m.toolText,
						link: m.link,
						marker: {
							enabled: n,
							fillColor: p,
							lineColor: {
								FCcolor: {
									color: y,
									alpha: H
								}
							},
							lineWidth: h,
							symbol: "circle"
						}
					});
					this.pointValueWatcher(c, r, q, s && z)
				} else b.data.push({
					y: null
				});
				s && (d = {
					type: "line",
					color: Q,
					showInLegend: !1,
					lineWidth: D,
					enableMouseTracking: !1,
					marker: {
						enabled: !1
					},
					data: this.getRegressionLineSeries(z, u, w),
					zIndex: 0
				}, b = [b, d])
			}
			if (!C) b.showInLegend = !1;
			return b
		},
		postSeriesAddition: function(a, b) {
			a.chart.clipBubbles = f(b.chart.clipbubbles, 1)
		},
		getPointStub: function(a, b, d, g, c, e) {
			var g = g[O],
				b = b === null ? b : g.numberFormatter.dataLabels(b),
				j, r = g.tooltipSepChar;
			g.showTooltip ? va(a.tooltext) !== void 0 ? c = wa(a.tooltext) : b === null ? c = !1 : (g.seriesNameInToolTip && (j = i(c && c.seriesname)), c = j ? j + r : B, c += d ? d + r : B, c += b, c += a.z ? r + a.z : B) : c = B;
			d = f(a.showvalue, e, g.showValues) ? i(a.displayvalue, a.name, a.label) !== void 0 ? wa(i(a.displayvalue, a.name, a.label)) : b : B;
			a = va(a.link);
			return {
				displayValue: d,
				toolText: c,
				link: a
			}
		}
	}, o.scatter);
	o("ssgrid", {
		friendlyName: "Grid Component",
		standaloneInit: !0,
		defaultSeriesType: "ssgrid",
		rendererId: "ssgrid",
		chart: function(a, b) {
			var z;
			var d = this.containerElement,
				g = this.dataObj,
				c = this.chartInstance,
				g = xa({}, g);
			g.chart = g.chart || g.graph || {};
			delete g.graph;
			var e, j, r, k = 0,
				n, m, p = [],
				l = g.chart,
				h = g.data,
				H = h && h.length,
				g = this.smartLabel,
				y = this.numberFormatter,
				w = d.offsetHeight,
				C = d.offsetWidth,
				q = {},
				x = 0,
				A = 0,
				P = (l.palette > 0 && l.palette < 6 ? l.palette : f(this.paletteIndex, 1)) - 1,
				d = {
					_FCconf: {
						0: {
							stack: {}
						},
						1: {
							stack: {}
						},
						x: {
							stack: {}
						},
						noWrap: !1,
						marginLeftExtraSpace: 0,
						marginRightExtraSpace: 0,
						marginBottomExtraSpace: 0,
						marginTopExtraSpace: 0,
						marimekkoTotal: 0
					},
					chart: {
						renderTo: d,
						ignoreHiddenSeries: !1,
						events: {},
						spacingTop: 0,
						spacingRight: 0,
						spacingBottom: 0,
						spacingLeft: 0,
						marginTop: 0,
						marginRight: 0,
						marginBottom: 0,
						marginLeft: 0,
						borderRadius: 0,
						borderColor: "#000000",
						borderWidth: 1,
						defaultSeriesType: "ssgrid",
						style: {
							fontFamily: i(l.basefont, "Verdana"),
							fontSize: Ha(l.basefontsize, 20) + Pa,
							color: i(l.basefontcolor, U.baseFontColor[P]).replace(/^#?([a-f0-9]+)/ig, "#$1")
						},
						plotBackgroundColor: Na
					},
					labels: {
						smartLabel: g
					},
					colors: ["AFD8F8", "F6BD0F", "8BBA00", "FF8E46", "008E8E", "D64646", "8E468E", "588526", "B3AA00", "008ED6", "9D080D", "A186BE", "CC6600", "FDC689", "ABA000", "F26D7D", "FFF200", "0054A6", "F7941C", "CC3300", "006600", "663300", "6DCFF6"],
					credits: {
						href: "http://www.fusioncharts.com?BS=FCHSEvalMark",
						text: t.CREDIT_STRING,
						enabled: this.creditLabel
					},
					legend: {
						enabled: !1
					},
					series: [],
					subtitle: {
						text: B
					},
					title: {
						text: B
					},
					tooltip: {
						enabled: !1
					},
					exporting: {
						buttons: {
							exportButton: {},
							printButton: {
								enabled: !1
							}
						}
					}
				},
				u = d[O],
				F = d.colors,
				v = d.colors.length,
				s = n = j = x = 0,
				A = k = m = 0;
			r = c.jsVars.cfgStore;
			c = d.chart;
			Ca(d.chart.style);
			c.events.click = this.linkClickFN;
			j = c.toolbar = {
				button: {}
			};
			n = j.button;
			n.scale = f(l.toolbarbuttonscale, 1.15);
			n.width = f(l.toolbarbuttonwidth, 15);
			n.height = f(l.toolbarbuttonheight, 15);
			n.radius = f(l.toolbarbuttonradius, 2);
			n.spacing = f(l.toolbarbuttonspacing, 5);
			n.fill = na(i(l.toolbarbuttoncolor, "ffffff"));
			n.labelFill = na(i(l.toolbarlabelcolor, "cccccc"));
			n.symbolFill = na(i(l.toolbarsymbolcolor, "ffffff"));
			n.hoverFill = na(i(l.toolbarbuttonhovercolor, "ffffff"));
			n.stroke = na(i(l.toolbarbuttonbordercolor, "bbbbbb"));
			n.symbolStroke = na(i(l.toolbarsymbolbordercolor, "9a9a9a"));
			n.strokeWidth = f(l.toolbarbuttonborderthickness, 1);
			n.symbolStrokeWidth = f(l.toolbarsymbolborderthickness, 1);
			m = n.symbolPadding = f(l.toolbarsymbolpadding, 5);
			n.symbolHPadding = f(l.toolbarsymbolhpadding, m);
			n.symbolVPadding = f(l.toolbarsymbolvpadding, m);
			m = j.position = i(l.toolbarposition, "tr").toLowerCase();
			switch (m) {
			case "tr":
			case "tl":
			case "br":
			case "bl":
				break;
			default:
				m = "tr"
			}
			n = j.hAlign = (B + l.toolbarhalign).toLowerCase() === "left" ? "l" : m.charAt(1);
			z = j.vAlign = (B + l.toolbarvalign).toLowerCase() === "bottom" ? "b" : m.charAt(0), m = z;
			j.hDirection = f(l.toolbarhdirection, n === "r" ? -1 : 1);
			j.vDirection = f(l.toolbarvdirection, m === "b" ? -1 : 1);
			j.vMargin = f(l.toolbarvmargin, 6);
			j.hMargin = f(l.toolbarhmargin, 10);
			j.x = f(l.toolbarx, n === "l" ? 0 : a);
			j.y = f(l.toolbary, m === "t" ? 0 : b);
			if (i(l.clickurl) !== void 0) c.link = l.clickurl, c.style.cursor = "pointer";
			q.showPercentValues = f(r.showpercentvalues, l.showpercentvalues, 0);
			q.numberItemsPerPage = i(r.numberitemsperpage, l.numberitemsperpage);
			q.showShadow = f(r.showshadow, l.showshadow, 0);
			q.baseFont = i(r.basefont, l.basefont, "Verdana");
			e = Ha(r.basefontsize, l.basefontsize, 10);
			q.baseFontSize = e + Pa;
			q.baseFontColor = V(i(r.basefontcolor, l.basefontcolor, U.baseFontColor[P]));
			q.alternateRowBgColor = V(i(r.alternaterowbgcolor, l.alternaterowbgcolor, U.altHGridColor[P]));
			q.alternateRowBgAlpha = i(r.alternaterowbgalpha, l.alternaterowbgalpha, U.altHGridAlpha[P]) + B;
			q.listRowDividerThickness = f(r.listrowdividerthickness, l.listrowdividerthickness, 1);
			q.listRowDividerColor = V(i(r.listrowdividercolor, l.listrowdividercolor, U.borderColor[P]));
			q.listRowDividerAlpha = f(r.listrowdivideralpha, l.listrowdivideralpha, U.altHGridAlpha[P]) + 15 + B;
			q.colorBoxWidth = f(r.colorboxwidth, l.colorboxwidth, 8);
			q.colorBoxHeight = f(r.colorboxheight, l.colorboxheight, 8);
			q.navButtonRadius = f(r.navbuttonradius, l.navbuttonradius, 7);
			q.navButtonColor = V(i(r.navbuttoncolor, l.navbuttoncolor, U.canvasBorderColor[P]));
			q.navButtonHoverColor = V(i(r.navbuttonhovercolor, l.navbuttonhovercolor, U.altHGridColor[P]));
			q.textVerticalPadding = f(r.textverticalpadding, l.textverticalpadding, 3);
			q.navButtonPadding = f(r.navbuttonpadding, l.navbuttonpadding, 5);
			q.colorBoxPadding = f(r.colorboxpadding, l.colorboxpadding, 10);
			q.valueColumnPadding = f(r.valuecolumnpadding, l.valuecolumnpadding, 10);
			q.nameColumnPadding = f(r.namecolumnpadding, l.namecolumnpadding, 5);
			q.borderThickness = f(r.borderthickness, l.borderthickness, 1);
			q.borderColor = V(i(r.bordercolor, l.bordercolor, U.borderColor[P]));
			q.borderAlpha = i(r.borderalpha, l.borderalpha, U.borderAlpha[P]) + B;
			q.bgColor = i(r.bgcolor, l.bgcolor, "FFFFFF");
			q.bgAlpha = i(r.bgalpha, l.bgalpha, Ba);
			q.bgRatio = i(r.bgratio, l.bgratio, Ba);
			q.bgAngle = i(r.bgangle, l.bgangle, ya);
			c.borderRadius = q.borderThickness / 16;
			c.borderWidth = q.borderThickness;
			c.borderColor = ba({
				FCcolor: {
					color: q.borderColor,
					alpha: q.borderAlpha
				}
			});
			c.backgroundColor = {
				FCcolor: {
					color: q.bgColor,
					alpha: q.bgAlpha,
					ratio: q.bgRatio,
					angle: q.bgAngle
				}
			};
			c.borderRadius = f(l.borderradius, 0);
			r = {
				fontFamily: q.baseFont,
				fontSize: q.baseFontSize,
				color: q.baseFontColor
			};
			Ca(r);
			g.setStyle(r);
			for (k = 0; k < H; k += 1) if (e = h[k],n = y.getCleanValue(e.value), m = wa(ka(e.label, e.name)), j = V(i(e.color, F[k % v])), i(e.alpha, l.plotfillalpha, Ba), m != B || n != null) p.push({
				value: n,
				label: m,
				color: j,
				displayValue:e.displayvalue,
				toolText:e.tooltext,
				showValue:e.showvalue
			}), x += n, A += 1;
			for (k = 0; k < A; k += 1) e = p[k], n = e.value, e.dataLabel = e.label,
			e.displayValue = (e.showValue==1)?(q.showPercentValues ? y.percentValue(n / x * 100) : e.displayValue||e.value):"",
			h = g.getOriSize(e.displayValue), s = Math.max(s, h.width + q.valueColumnPadding);
			q.numberItemsPerPage ? q.numberItemsPerPage >= A ? (q.numberItemsPerPage = A, n = w / q.numberItemsPerPage, j = A) : (y = w, y -= 2 * (q.navButtonPadding + q.navButtonRadius), j = q.numberItemsPerPage, n = y / j) : (x = parseInt(r.lineHeight, 10), x += 2 * q.textVerticalPadding, x = Math.max(x, q.colorBoxHeight), w / x >= A ? (n = w / A, j = A) : (y = w, y -= 2 * (q.navButtonPadding + q.navButtonRadius), j = Math.floor(y / x), n = y / j));
			m = C - q.colorBoxPadding - q.colorBoxWidth - q.nameColumnPadding - s - q.valueColumnPadding;
			k = q.colorBoxPadding + q.colorBoxWidth + q.nameColumnPadding;
			y = i(l.basefont, "Verdana");
			h = Ha(l.basefontsize, 10);
			P = i(l.basefontcolor, U.baseFontColor[P]);
			H = i(l.outcnvbasefont, y);
			e = Ha(l.outcnvbasefontsize, h);
			s = e + Pa;
			l = i(l.outcnvbasefontcolor, P).replace(/^#?([a-f0-9]+)/ig, "#$1");
			h += Pa;
			P = P.replace(/^#?([a-f0-9]+)/ig, "#$1");
			u.trendStyle = u.outCanvasStyle = {
				fontFamily: H,
				color: l,
				fontSize: s
			};
			Ca(u.trendStyle);
			u.inCanvasStyle = {
				fontFamily: y,
				fontSize: h,
				color: P
			};
			d.tooltip.style = {
				fontFamily: y,
				fontSize: h,
				lineHeight: void 0,
				color: P
			};
			d.tooltip.shadow = !1;
			c.height = w;
			c.width = C;
			c.rowHeight = n;
			c.labelX = k;
			c.colorBoxWidth = q.colorBoxWidth;
			c.colorBoxHeight = q.colorBoxHeight;
			c.colorBoxX = q.colorBoxPadding;
			c.valueX = q.colorBoxPadding + q.colorBoxWidth + q.nameColumnPadding + m + q.valueColumnPadding;
			c.valueColumnPadding = q.valueColumnPadding;
			c.textStyle = r;
			c.listRowDividerAttr = {
				"stroke-width": q.listRowDividerThickness,
				stroke: {
					FCcolor: {
						color: q.listRowDividerColor,
						alpha: q.listRowDividerAlpha
					}
				}
			};
			c.alternateRowColor = {
				FCcolor: {
					color: q.alternateRowBgColor,
					alpha: q.alternateRowBgAlpha
				}
			};
			c.navButtonRadius = q.navButtonRadius;
			c.navButtonPadding = q.navButtonPadding;
			c.navButtonColor = q.navButtonColor;
			c.navButtonHoverColor = q.navButtonHoverColor;
			c.lineHeight = parseInt(r.lineHeight, 10);
			l = [];
			w = 0;
			q = !0;
			for (k = 0; k < A & j != 0; k += 1) k % j == 0 && (l.push({
				data: [],
				visible: q
			}), q = !1, w += 1), e = p[k],C = g.getSmartText(e.dataLabel, m, n),l[w - 1].data.push({
				label: C.text,
				originalText: e.toolText,
				displayValue: e.displayValue,
				y: e.value,
				color: e.color
			});
			d.series = l;
			o.base.parseExportOptions.call(this, d);
			d.tooltip.enabled = !! d.exporting.enabled;
			return d
		},
		creditLabel: ra
	}, o.base);
	o("renderer.bubble", {
		drawPlotBubble: function(a, b) {
			var d = this,
				g = d.options,
				c = g.chart,
				e = g.plotOptions.series,
				j = d.paper,
				r = d.elements,
				k = a.items,
				n = a.graphics = a.graphics || [],
				m = d.xAxis[b.xAxis || 0],
				p = d.yAxis[b.yAxis || 0],
				l = a.data,
				h = (g.tooltip || {}).enabled !== !1,
				e = isNaN(+e.animation) && e.animation.duration || e.animation * 1E3,
				o = b.visible === !1 ? "hidden" : "visible",
				g = g.plotOptions.bubble,
				y = g.zMax,
				g = g.bubbleScale,
				H = ha(d.canvasHeight, d.canvasWidth) / 8,
				y = R(y),
				C, q, x, w, P, i, F, v, s, z, u = d.layers,
				Q = u.dataset = u.dataset || j.group("dataset-orphan");
			u.datalabels = u.datalabels || j.group("datalables").insertAfter(Q);
			var u = u.tracker,
				D, L, Q = Q.bubble = Q.bubble || j.group("bubble", Q);
			c.clipBubbles && !Q.attrs["clip-rect"] && Q.attr({
				"clip-rect": r["clip-canvas"]
			});
			r = 0;
			for (C = l.length; r < C; r += 1) {
				q = l[r];
				s = z = L = null;
				v = q.marker;
				if (q.y !== null && v && v.enabled) {
					x = q.link;
					c = q.toolText;
					w = f(q.x, r);
					P = q.y;
					F = p.getAxisPosition(P);
					i = m.getAxisPosition(w);
					s = R(q.z);
					D = A(s * H / y) * g;
					s = j.circle(i, F, 0, Q).attr({
						fill: ba(v.fillColor),
						"stroke-width": v.lineWidth,
						stroke: ba(v.lineColor),
						visibility: o
					}).animate({
						r: D || 0
					}, e, "easeOut");
					if (x || h) D < La && (D = La), z = j.circle(i, F, D, u).attr({
						cursor: x ? "pointer" : "",
						stroke: la,
						"stroke-width": v.lineWidth,
						fill: la,
						ishot: !! x,
						visibility: o
					}).tooltip(c).data("link", x).click(function() {
						var a = this.data("link");
						a && d.linkClickFN.call({
							link: a
						}, d)
					});
					k[r] = {
						index: r,
						x: w,
						y: P,
						z: q.z,
						value: P,
						graphic: s,
						dataLabel: L,
						tracker: z
					};
					L = d.drawPlotLineLabel(a, b, r, i, F)
				} else k[r] = {
					index: r,
					x: w,
					y: P
				};
				L && n.push(L);
				s && n.push(s);
				z && n.push(z)
			}
			a.visible = b.visible !== !1;
			return a
		}
	}, o["renderer.cartesian"]);
	o("renderer.ssgrid", {
		drawGraph: function() {
			var a = this.options.series,
				b = this.elements,
				d = b.plots,
				c = a.length,
				e;
			if (!d) d = this.plots = this.plots || [], b.plots = d;
			this.drawSSGridNavButton();
			for (e = 0; e < c; e++) {
				if (!(b = d[e])) d.push(b = {
					items: [],
					data: a[e].data
				});
				a[e].data && a[e].data.length && this.drawPlot(b, a[e])
			}
			c > 1 && this.nenagitePage(0)
		},
		drawPlot: function(a) {
			var b = a.data,
				d = this.paper,
				g = this.options.chart,
				e = g.colorBoxHeight,
				h = g.colorBoxWidth,
				j = g.colorBoxX,
				r = g.labelX,
				aa = g.valueX,
				n = g.rowHeight,
				m = g.width,
				p = g.listRowDividerAttr,
				l = p["stroke-width"],
				p = ba(p.stroke),
				o = l % 2 / 2,
				H = g.textStyle,
				y = this.layers,
				y = y.dataset = y.dataset || d.group("dataset-orphan"),
				g = ba(g.alternateRowColor),
				a = a.items,
				w = 0,
				C, q, x, f;
			if (!b || !b.length) b = [];
			p = {
				stroke: p,
				"stroke-width": l
			};
			f = 0;
			for (l = b.length; f < l; f += 1) if (x = b[f], q = x.y, C = a[f] = {
				index: f,
				value: q,
				graphic: null,
				dataLabel: null,
				dataValue: null,
				alternateRow: null,
				listRowDivider: null,
				hot: null
			}, !(q === null || q === void 0)) {
				if (f % 2 === 0) C.alternateRow = d.rect(0, w, m, n, 0, y).attr({
					fill: g,
					"stroke-width": 0
				});
				q = A(w) + o;
				C.listRowDivider = d.path([c, 0, q, k, m, q], y).attr(p);
				C.graphic = d.rect(j, w + n / 2 - e / 2, h, e, 0, y).attr({
					fill: x.color,
					"stroke-width": 0,
					stroke: "#000000"
				});
				q = C.dataLabel = d.text().attr({
					text: x.label,
					title: x.originalText || "",
					x: r,
					y: w + n / 2,
					fill: H.color,
					"text-anchor": "start"
				}).css(H);
				y.appendChild(q);
				C = C.dataValue = d.text().attr({
					text: x.displayValue,
					title: x.originalText || "",
					x: aa,
					y: w + n / 2,
					fill: H.color,
					"text-anchor": "start"
				}).css(H);
				y.appendChild(C);
				w += n
			}
			q = A(w) + o;
			d.path([c, 0, q, k, m, q], y).attr(p)
		},
		drawSSGridNavButton: function(a) {
			var a = this,
				b = a.paper,
				d = a.options,
				g = d.chart,
				e = d.series,
				h = g.navButtonColor,
				j = g.navButtonHoverColor,
				d = g.navButtonRadius,
				r = d * 0.67,
				aa = g.navButtonPadding + r + (e && e[0].data && e[0].data.length * g.rowHeight) + d * 0.5,
				g = g.width - 20,
				n, m, p;
			if (e.length > 1) {
				var l = a.naviigator = b.group("navigation");
				a.navElePrv = e = b.group(l);
				n = b.path([c, 20, aa, k, 20 + d + r, aa - r, 20 + d, aa, 20 + d + r, aa + r, "Z"]).attr({
					fill: h,
					"stroke-width": 0,
					cursor: "pointer"
				});
				e.appendChild(n);
				p = b.circle(20 + d, aa, d).attr({
					fill: Na,
					"stroke-width": 0,
					cursor: "pointer"
				}).mouseover(function() {
					n.attr({
						fill: j,
						cursor: "pointer"
					})
				}).mouseout(function() {
					n.attr({
						fill: h
					})
				}).click(function() {
					a.nenagitePage(-1)
				});
				e.appendChild(p);
				a.navEleNxt = e = b.group(l);
				m = b.path([c, g, aa, k, g - d - r, aa - r, g - d, aa, g - d - r, aa + r, "Z"]).attr({
					fill: h,
					"stroke-width": 0,
					cursor: "pointer"
				});
				e.appendChild(m);
				b = b.circle(g - d, aa, d).attr({
					fill: Na,
					"stroke-width": 0,
					cursor: "pointer"
				}).mouseover(function() {
					m.attr({
						fill: j
					})
				}).mouseout(function() {
					m.attr({
						fill: h
					})
				}).click(function() {
					a.nenagitePage(1)
				});
				e.appendChild(b)
			}
		},
		nenagitePage: function(a) {
			var b = this.plots,
				d = b.length,
				a = (this.currentSeriesIndex || 0) + (a || 0),
				c;
			if (b[a]) {
				for (c = d; c--;) qa(b[c].items, function(a) {
					a.graphic && a.graphic.hide();
					a.dataLabel && a.dataLabel.hide();
					a.dataValue && a.dataValue.hide();
					a.alternateRow && a.alternateRow.hide();
					a.listRowDivider && a.listRowDivider.hide()
				});
				qa(b[a].items, function(a) {
					a.graphic && a.graphic.show();
					a.dataLabel && a.dataLabel.show();
					a.dataValue && a.dataValue.show();
					a.alternateRow && a.alternateRow.show();
					a.listRowDivider && a.listRowDivider.show()
				});
				this.currentSeriesIndex = a;
				a === 0 ? this.navElePrv.hide() : this.navElePrv.show();
				a === d - 1 ? this.navEleNxt.hide() : this.navEleNxt.show()
			}
		}
	}, o["renderer.root"]);
	Fa.prototype = {
		getArcPath: function(a, b, d, c, k, h, j, r, aa, n) {
			return d == k && c == h ? [] : [e, j, r, 0, n, aa, k, h]
		},
		parseColor: function(a, b) {
			var d, c, e, k, j, r, h, n, m, p, l = b / 2,
				o, w, y, f, H;
			H = 3;
			this.use3DLighting ? (d = ca(a, 80), c = ca(a, 75), r = ea(a, 85), h = ea(a, 70), n = ea(a, 40), m = ea(a, 50), ea(a, 30), p = ea(a, 65), ca(a, 85), e = ca(a, 69), k = ca(a, 75), j = ca(a, 95)) : (H = 10, d = ca(a, 90), c = ca(a, 87), r = ea(a, 93), h = ea(a, 87), n = ea(a, 80), p = m = ea(a, 85), ea(a, 80), j = ca(a, 85), e = ca(a, 75), k = ca(a, 80));
			o = c + K + r + K + h + K + r + K + c;
			y = b + K + b + K + b + K + b + K + b;
			w = c + K + a + K + r + K + a + K + c;
			f = l + K + l + K + l + K + l + K + l;
			n = c + K + a + K + n + K + a + K + c;
			e = k + K + r + K + m + K + r + K + e;
			k = "FFFFFF" + K + "FFFFFF" + K + "FFFFFF" + K + "FFFFFF" + K + "FFFFFF";
			H = 0 + K + l / H + K + b / H + K + l / H + K + 0;
			return {
				frontOuter: {
					FCcolor: {
						gradientUnits: "userSpaceOnUse",
						x1: this.leftX,
						y1: 0,
						x2: this.rightX,
						y2: 0,
						color: e,
						alpha: y,
						angle: 0,
						ratio: "0,20,15,15,50"
					}
				},
				backOuter: {
					FCcolor: {
						gradientUnits: "userSpaceOnUse",
						x1: this.leftX,
						y1: 0,
						x2: this.rightX,
						y2: 0,
						color: n,
						alpha: f,
						angle: 0,
						ratio: "0,62,8,8,22"
					}
				},
				frontInner: {
					FCcolor: {
						gradientUnits: "userSpaceOnUse",
						x1: this.leftInnerX,
						y1: 0,
						x2: this.rightInnerX,
						y2: 0,
						color: w,
						alpha: f,
						angle: 0,
						ratio: "0,25,5,5,65"
					}
				},
				backInner: {
					FCcolor: {
						gradientUnits: "userSpaceOnUse",
						x1: this.leftInnerX,
						y1: 0,
						x2: this.rightInnerX,
						y2: 0,
						color: o,
						alpha: y,
						angle: 0,
						ratio: "0,62,8,8,22"
					}
				},
				topBorder: {
					FCcolor: {
						gradientUnits: "userSpaceOnUse",
						x1: this.leftX,
						y1: 0,
						x2: this.rightX,
						y2: 0,
						color: k,
						alpha: H,
						angle: 0,
						ratio: "0,20,15,15,50"
					}
				},
				topInnerBorder: {
					FCcolor: {
						gradientUnits: "userSpaceOnUse",
						x1: this.leftInnerX,
						y1: 0,
						x2: this.rightInnerX,
						y2: 0,
						color: k,
						alpha: H,
						angle: 0,
						ratio: "0,50,15,15,20"
					}
				},
				top: Z ? {
					FCcolor: {
						gradientUnits: "userSpaceOnUse",
						radialGradient: !0,
						cx: this.cx,
						cy: this.cy,
						r: this.rx,
						fx: this.cx - 0.3 * this.rx,
						fy: this.cy + this.ry * 1.2,
						color: p + K + j,
						alpha: b + K + b,
						ratio: "0,100"
					}
				} : {
					FCcolor: {
						gradientUnits: "objectBoundingBox",
						color: h + K + h + K + r + K + c,
						alpha: b + K + b + K + b + K + b,
						angle: -72,
						ratio: "0,8,15,77"
					}
				},
				bottom: ba(na(a, l)),
				startSlice: ba(na(d, b)),
				endSlice: ba(na(d, b))
			}
		},
		rotate: function(a) {
			if (!this.hasOnePoint) {
				for (var b = this.pointElemStore, d = 0, c = b.length, e; d < c; d += 1) e = b[d], e = e._confObject, e.start += a, e.end += a, this.updateSliceConf(e);
				this.refreshDrawing()
			}
		},
		refreshDrawing: function() {
			return function() {
				var a = this.slicingWallsArr,
					b = 0,
					d, c = a.length,
					e, k, j, r, h = this.slicingWallsFrontGroup,
					n = this.slicingWallsBackGroup;
				a: {
					var m = a[0] && a[0]._conf.index,
						p, l;
					r = m <= W;
					e = 1;
					for (d = a.length; e < d; e += 1) if (l = a[e]._conf.index, p = l <= W, p != r || l < m) break a;
					e = 0
				}
				for (; b < c; b += 1, e += 1) e === c && (e = 0),
				d = a[e],
				r = d._conf.index,
				r < S ? h.appendChild(d) : r <= W ? (k ? d.insertBefore(k) : h.appendChild(d), k = d) : r < Ua ? (j ? d.insertBefore(j) : n.appendChild(d), j = d) : n.appendChild(d)
			}
		}(),
		updateSliceConf: function(a, b) {
			var d = this.getArcPath,
				g = a.start,
				o = a.end,
				H = sa(g),
				j = sa(o),
				r, aa, n, m, p, l, f, i, y, A, C, q, x, u, X, ha, F = this.cx,
				v = this.cy,
				s = this.rx,
				z = this.ry,
				Y = s + (Z ? -1 : 2),
				Q = z + (Z ? -1 : 2),
				D = this.innerRx,
				L = this.innerRy,
				G = this.depth,
				I = this.depthY,
				M = a.elements,
				t, N, da, K;
			r = w(H);
			aa = P(H);
			n = w(j);
			m = P(j);
			p = F + s * r;
			l = v + z * aa;
			f = F + Y * r;
			i = v + Q * aa;
			t = l + G;
			N = F + s * n;
			da = v + z * m;
			y = F + Y * n;
			A = v + Q * m;
			K = da + G;
			this.isDoughnut ? (C = F + D * r, q = v + L * aa, X = q + G, x = F + D * n, u = v + L * m, ha = u + G, a.startSlice = [c, p, l, k, p, t, C, X, C, q, h], a.endSlice = [c, N, da, k, N, K, x, ha, x, u, h]) : (a.startSlice = [c, p, l, k, p, t, F, I, F, v, h], a.endSlice = [c, N, da, k, N, K, F, I, F, v, h]);
			if (Z) {
				d = (H > j ? fa : 0) + j - H;
				a.clipTopPath = this.isDoughnut ? [c, p, l, e, s, z, 0, d > W ? 1 : 0, 1, N, da, k, x, u, e, D, L, 0, d > W ? 1 : 0, 0, C, q, h] : [c, p, l, e, s, z, 0, d > W ? 1 : 0, 1, N, da, k, this.cx, this.cy, h];
				a.clipOuterFrontPath1 = this.clipPathforNoClip;
				a.clipTopBorderPath = [c, f, i, e, Y, Q, 0, d > W ? 1 : 0, 1, y, A, k, N, da, N, da + 1, e, s, z, 0, d > W ? 1 : 0, 0, p, l + 1, k, p, l, h];
				if (g != o) if (H > j) if (H < W) {
					if (a.clipOuterFrontPath = [c, this.rightX, v, e, s, z, 0, 0, 1, N, da, "v", G, e, s, z, 0, 0, 0, this.rightX, v + G, h], a.clipOuterFrontPath1 = [c, this.leftX, v, e, s, z, 0, 0, 0, p, l, "v", G, e, s, z, 0, 0, 1, this.leftX, v + G, h], a.clipOuterBackPath = [c, this.rightX, v, e, s, z, 0, 1, 0, this.leftX, v, "v", G, e, s, z, 0, 1, 1, this.rightX, v + G, h], this.isDoughnut) a.clipInnerBackPath = [c, this.rightInnerX, v, e, D, L, 0, 1, 0, this.leftInnerX, v, "v", G, e, D, L, 0, 1, 1, this.rightInnerX, v + G, h], a.clipInnerFrontPath = [c, this.rightInnerX, v, e, D, L, 0, 0, 1, x, u, "v", G, e, D, L, 0, 0, 0, this.rightInnerX, v + G, h, c, this.leftInnerX, v, e, D, L, 0, 0, 0, C, q, "v", G, e, D, L, 0, 0, 1, this.leftInnerX, v + G, h]
				} else if (j > W) {
					if (a.clipOuterFrontPath = [c, this.rightX, v, e, s, z, 0, 1, 1, this.leftX, v, "v", G, e, s, z, 0, 1, 0, this.rightX, v + G, h], a.clipOuterBackPath = [c, this.leftX, v, e, s, z, 0, 0, 1, N, da, "v", G, e, s, z, 0, 0, 0, this.leftX, v + G, h, c, this.rightX, v, e, s, z, 0, 0, 0, p, l, "v", G, e, s, z, 0, 0, 1, this.rightX, v + G, h], this.isDoughnut) a.clipInnerFrontPath = [c, this.rightInnerX, v, e, D, L, 0, 1, 1, this.leftInnerX, v, "v", G, e, D, L, 0, 1, 0, this.rightInnerX, v + G, h], a.clipInnerBackPath = [c, this.leftInnerX, v, e, D, L, 0, 0, 1, x, u, "v", G, e, D, L, 0, 0, 0, this.leftInnerX, v + G, h, c, this.rightInnerX, v, e, D, L, 0, 0, 0, C, q, "v", G, e, D, L, 0, 0, 1, this.rightInnerX, v + G, h]
				} else {
					if (a.clipOuterFrontPath = [c, this.rightX, v, e, s, z, 0, 0, 1, N, da, "v", G, e, s, z, 0, 0, 0, this.rightX, v + G, h], a.clipOuterBackPath = [c, p, l, e, s, z, 0, 0, 1, this.rightX, v, "v", G, e, s, z, 0, 0, 0, p, t, h], this.isDoughnut) a.clipInnerFrontPath = [c, this.rightInnerX, v, e, D, L, 0, 0, 1, x, u, "v", G, e, D, L, 0, 0, 0, this.rightInnerX, v + G, h], a.clipInnerBackPath = [c, C, q, e, D, L, 0, 0, 1, this.rightInnerX, v, "v", G, e, D, L, 0, 0, 0, C, X, h]
				} else if (H < W) if (j > W) {
					if (a.clipOuterFrontPath = [c, p, l, e, s, z, 0, 0, 1, this.leftX, v, "v", G, e, s, z, 0, 0, 0, p, t, h], a.clipOuterBackPath = [c, this.leftX, v, e, s, z, 0, 0, 1, N, da, "v", G, e, s, z, 0, 0, 0, this.leftX, v + G, h], this.isDoughnut) a.clipInnerFrontPath = [c, C, q, e, D, L, 0, 0, 1, this.leftInnerX, v, "v", G, e, D, L, 0, 0, 0, C, X, h], a.clipInnerBackPath = [c, this.leftInnerX, v, e, D, L, 0, 0, 1, x, u, "v", G, e, D, L, 0, 0, 0, this.leftInnerX, v + G, h]
				} else {
					if (a.clipOuterFrontPath = [c, p, l, e, s, z, 0, 0, 1, N, da, "v", G, e, s, z, 0, 0, 0, p, t, h], a.clipOuterBackPath = this.clipPathforNoClip, this.isDoughnut) a.clipInnerFrontPath = [c, C, q, e, D, L, 0, 0, 1, x, u, "v", G, e, D, L, 0, 0, 0, C, X, h], a.clipInnerBackPath = this.clipPathforNoClip
				} else {
					if (a.clipOuterFrontPath = this.clipPathforNoClip, a.clipOuterBackPath = [c, p, l, e, s, z, 0, 0, 1, N, da, "v", G, e, s, z, 0, 0, 0, p, t, h], this.isDoughnut) a.clipInnerFrontPath = this.clipPathforNoClip, a.clipInnerBackPath = [c, C, q, e, D, L, 0, 0, 1, x, u, "v", G, e, D, L, 0, 0, 0, C, X, h]
				} else a.clipOuterFrontPath = a.clipOuterBackPath = a.clipInnerBackPath = a.clipInnerFrontPath = this.clipPathforNoClip;
				if (!b) {
					a.elements.startSlice._conf.index = H;
					a.elements.endSlice._conf.index = j;
					a.elements.frontOuter._conf.index = Ea(j, H);
					if (a.elements.frontOuter1) a.elements.frontOuter1._conf.index = H, a.elements.frontOuter1.attr("litepath", [a.clipOuterFrontPath1]);
					a.thisElement.attr("litepath", [a.clipTopPath]);
					a.elements.bottom.attr("litepath", [a.clipTopPath]);
					a.elements.bottomBorder.attr("litepath", [a.clipTopPath]);
					a.elements.topBorder && a.elements.topBorder.attr("litepath", [a.clipTopBorderPath]);
					a.elements.frontOuter.attr("litepath", [a.clipOuterFrontPath]);
					a.elements.backOuter.attr("litepath", [a.clipOuterBackPath]);
					if (this.isDoughnut) a.elements.backInner.attr("litepath", [a.clipInnerBackPath]), a.elements.frontInner.attr("litepath", [a.clipInnerFrontPath]), a.elements.backInner._conf.index = Ea(j, H);
					this.hasOnePoint ? (a.elements.startSlice.hide(), a.elements.endSlice.hide()) : (a.elements.startSlice.attr("litepath", [a.startSlice]).show(), a.elements.endSlice.attr("litepath", [a.endSlice]).show())
				}
			} else {
				f = this.moveCmdArr;
				i = this.lineCmdArr;
				y = this.closeCmdArr;
				var E = this.centerPoint;
				A = this.leftPoint;
				var Y = this.topPoint,
					Q = this.rightPoint,
					G = this.bottomPoint,
					S = this.leftDepthPoint,
					T = this.rightDepthPoint;
				r = this.leftInnerPoint;
				aa = this.rightInnerPoint;
				n = this.leftInnerDepthPoint;
				m = this.rightInnerDepthPoint;
				a.clipOuterFrontPath1 = [];
				if (g != o) {
					if (H > j ? H < W ? (g = d(F, v, p, l, this.leftX, v, s, z, 1, 0), o = d(F, v, this.leftX, v, this.rightX, v, s, z, 1, 0), da = d(F, v, this.rightX, v, N, da, s, z, 1, 0), a.clipOuterBackPath = f.concat(A, o, i, T, d(F, I, this.rightX, I, this.leftX, I, s, z, 0, 0), y), a.clipOuterFrontPath1 = f.concat([p, l], g, i, S, d(F, I, this.leftX, I, p, t, s, z, 0, 0), y), a.clipOuterFrontPath = f.concat(Q, da, i, [N, K], d(F, I, N, K, this.rightX, I, s, z, 0, 0), y), a.clipTopBorderPath = f.concat([p, l], g, o, da), this.isDoughnut ? (p = d(F, v, x, u, this.rightInnerX, v, D, L, 0, 0), l = d(F, v, this.rightInnerX, v, this.leftInnerX, v, D, L, 0, 0), q = d(F, v, this.leftInnerX, v, C, q, D, L, 0, 0), a.clipInnerBackPath = f.concat(aa, l, i, n, d(F, I, this.leftInnerX, I, this.rightInnerX, I, D, L, 1, 0), y), a.clipInnerFrontPath = f.concat(r, q, i, [C, X], d(F, I, C, X, this.leftInnerX, I, D, L, 1, 0), y, f, [x, u], p, i, m, d(F, I, this.rightInnerX, I, x, ha, D, L, 1, 0), y), a.clipTopPath = a.clipTopBorderPath.concat(i, [x, u], p, l, q, y), a.clipTopBorderPath = a.clipTopBorderPath.concat(f, [x, u], p, l, q)) : a.clipTopPath = a.clipTopBorderPath.concat(i, E, y)) : j > W ? (g = d(F, v, p, l, this.rightX, v, s, z, 1, 0), o = d(F, v, this.rightX, v, this.leftX, v, s, z, 1, 0), da = d(F, v, this.leftX, v, N, da, s, z, 1, 0), a.clipOuterFrontPath = f.concat(Q, o, i, S, d(F, I, this.leftX, I, this.rightX, I, s, z, 0, 0), y), a.clipOuterBackPath = f.concat([p, l], g, i, T, d(F, I, this.rightX, I, p, t, s, z, 0, 0), y, f, A, da, i, [N, K], d(F, I, N, K, this.leftX, I, s, z, 0, 0), y), a.clipTopBorderPath = f.concat([p, l], g, o, da), this.isDoughnut ? (p = d(F, v, x, u, this.leftInnerX, v, D, L, 0, 0), l = d(F, v, this.leftInnerX, v, this.rightInnerX, v, D, L, 0, 0), q = d(F, v, this.rightInnerX, v, C, q, D, L, 0, 0), a.clipInnerFrontPath = f.concat(r, l, i, m, d(F, I, this.rightInnerX, I, this.leftInnerX, I, D, L, 1, 0), y), a.clipInnerBackPath = f.concat(aa, q, i, [C, X], d(F, I, C, X, this.rightInnerX, I, D, L, 1, 0), y, f, [x, u], p, i, n, d(F, I, this.leftInnerX, I, x, ha, D, L, 1, 0), y), a.clipTopPath = a.clipTopBorderPath.concat(i, [x, u], p, l, q, y), a.clipTopBorderPath = a.clipTopBorderPath.concat(f, [x, u], p, l, q)) : a.clipTopPath = a.clipTopBorderPath.concat(i, E, y)) : (g = d(F, v, p, l, this.rightX, v, s, z, 1, 0), o = d(F, v, this.rightX, v, N, da, s, z, 1, 0), a.clipOuterFrontPath = f.concat(Q, o, i, [N, K], d(F, I, N, K, this.rightX, I, s, z, 0, 0), y), a.clipOuterBackPath = f.concat([p, l], g, i, T, d(F, I, this.rightX, I, p, t, s, z, 0, 0), y), a.clipTopBorderPath = f.concat([p, l], g, o), this.isDoughnut ? (p = d(F, v, x, u, this.rightInnerX, v, D, L, 0, 0), l = d(F, v, this.rightInnerX, v, C, q, D, L, 0, 0), a.clipInnerFrontPath = f.concat([x, u], p, i, m, d(F, I, this.rightInnerX, I, x, ha, D, L, 1, 0), y), a.clipInnerBackPath = f.concat(aa, l, i, [C, X], d(F, I, C, X, this.rightInnerX, I, D, L, 1, 0), y), a.clipTopPath = a.clipTopBorderPath.concat(i, [x, u], p, l, y), a.clipTopBorderPath = a.clipTopBorderPath.concat(f, [x, u], p, l)) : a.clipTopPath = a.clipTopBorderPath.concat(i, E, y)) : H < W ? j > W ? (g = d(F, v, p, l, this.leftX, v, s, z, 1, 0), o = d(F, v, this.leftX, v, N, da, s, z, 1, 0), a.clipOuterBackPath = f.concat(A, o, i, [N, K], d(F, I, N, K, this.leftX, I, s, z, 0, 0), y), a.clipOuterFrontPath = f.concat([p, l], g, i, S, d(F, I, this.leftX, I, p, t, s, z, 0, 0), y), a.clipTopBorderPath = f.concat([p, l], g, o), this.isDoughnut ? (p = d(F, v, x, u, this.leftInnerX, v, D, L, 0, 0), l = d(F, v, this.leftInnerX, v, C, q, D, L, 0, 0), a.clipInnerBackPath = f.concat([x, u], p, i, n, d(F, I, this.leftInnerX, I, x, ha, D, L, 1, 0), y), a.clipInnerFrontPath = f.concat(r, l, i, [C, X], d(F, I, C, X, this.leftInnerX, I, D, L, 1, 0), y), a.clipTopPath = a.clipTopBorderPath.concat(i, [x, u], p, l, y), a.clipTopBorderPath = a.clipTopBorderPath.concat(f, [x, u], p, l)) : a.clipTopPath = a.clipTopBorderPath.concat(i, E, y)) : (g = d(F, v, p, l, N, da, s, z, 1, 0), a.clipOuterBackPath = f.concat([p, l]), a.clipTopBorderPath = a.clipOuterBackPath.concat(g), a.clipOuterFrontPath = a.clipTopBorderPath.concat(i, [N, K], d(F, I, N, K, p, t, s, z, 0, 0), y), this.isDoughnut ? (p = d(F, v, x, u, C, q, D, L, 0, 0), a.clipInnerBackPath = f.concat([x, u]), a.clipTopPath = a.clipTopBorderPath.concat(i, [x, u], p, y), a.clipTopBorderPath = a.clipTopBorderPath.concat(f, [x, u], p), a.clipInnerFrontPath = a.clipInnerBackPath.concat(p, i, [C, X], d(F, I, C, X, x, ha, D, L, 1, 0), y)) : a.clipTopPath = a.clipTopBorderPath.concat(i, E, y)) : (g = d(F, v, p, l, N, da, s, z, 1, 0), a.clipOuterFrontPath = f.concat([p, l]), a.clipTopBorderPath = a.clipOuterFrontPath.concat(g), a.clipOuterBackPath = a.clipTopBorderPath.concat(i, [N, K], d(F, I, N, K, p, t, s, z, 0, 0), y), this.isDoughnut ? (p = d(F, v, x, u, C, q, D, L, 0, 0), a.clipInnerFrontPath = f.concat([x, u]), a.clipTopPath = a.clipTopBorderPath.concat(i, [x, u], p, y), a.clipTopBorderPath = a.clipTopBorderPath.concat(a.clipInnerFrontPath, p), a.clipInnerBackPath = a.clipInnerFrontPath.concat(p, i, [C, X], d(F, I, C, X, x, ha, D, L, 1, 0), y)) : a.clipTopPath = a.clipTopBorderPath.concat(i, E, y)), g = f.concat(A, i, Q), p = f.concat(Y, i, G), a.clipTopPath = a.clipTopPath.concat(g, p), a.clipOuterFrontPath = a.clipOuterFrontPath.concat(g), a.clipOuterFrontPath1 = a.clipOuterFrontPath1.concat(g), a.clipOuterBackPath = a.clipOuterBackPath.concat(g), this.isDoughnut) p = f.concat(r, i, aa), a.clipInnerFrontPath = a.clipInnerFrontPath.concat(p), a.clipInnerBackPath = a.clipInnerBackPath.concat(p)
				} else if (a.clipTopPath = a.clipOuterFrontPath = a.clipOuterBackPath = [], this.isDoughnut) a.clipInnerFrontPath = a.clipInnerBackPath = [];
				if (!b) {
					a.elements.startSlice._conf.index = H;
					a.elements.endSlice._conf.index = j;
					a.elements.frontOuter._conf.index = Ea(j, H);
					if (a.elements.frontOuter1) a.elements.frontOuter1._conf.index = H, M.frontOuter1.attr({
						path: a.clipOuterFrontPath1
					});
					a.thisElement.attr({
						path: a.clipTopPath
					});
					M.topBorder.attr({
						path: a.clipTopBorderPath
					});
					M.bottom.attr({
						path: a.clipTopPath
					});
					M.bottomBorder.attr({
						path: a.clipTopBorderPath
					});
					M.frontOuter.attr({
						path: a.clipOuterFrontPath
					});
					M.backOuter.attr({
						path: a.clipOuterBackPath
					});
					this.isDoughnut && (M.frontInner.attr({
						path: a.clipInnerFrontPath
					}), M.backInner.attr({
						path: a.clipInnerBackPath
					}));
					this.hasOnePoint ? (a.elements.startSlice.hide(), a.elements.endSlice.hide()) : (a.elements.startSlice.attr({
						path: a.startSlice
					}).show(), a.elements.endSlice.attr({
						path: a.endSlice
					}).show())
				}
			}
		},
		createSlice: function() {
			var a = {
				stroke: !0,
				strokeWidth: !0,
				"stroke-width": !0,
				dashstyle: !0,
				"stroke-dasharray": !0,
				translateX: !0,
				translateY: !0,
				"stroke-opacity": !0,
				transform: !0,
				fill: !0,
				opacity: !0,
				ishot: !0,
				start: !0,
				end: !0,
				cursor: !0
			},
				b = function(b, d) {
					var c, e, g = this,
						k = g._confObject,
						l, h = k.elements,
						f, o, J = k.pie3DManager;
					typeof b === "string" && d !== void 0 && d !== null && (c = b, b = {}, b[c] = d);
					if (!b || typeof b === "string") g = g._attr(b);
					else {
						if (b.cx !== void 0) b.start = b.cx;
						if (b.cy !== void 0) b.end = b.cy;
						for (c in b) if (e = b[c], a[c]) if (k[c] = e, c === "ishot" || c === "cursor") {
							l = {};
							l[c] = e;
							for (f in h) h[f].attr(l);
							g._attr(l)
						} else if (c === "transform") {
							for (f in h) h[f].attr({
								transform: b[c]
							});
							g._attr({
								transform: b[c]
							})
						} else if (c === "stroke" || c === "strokeWidth" || c === "stroke-width" || c === "dashstyle" || c === "stroke-dasharray") l = {}, l[c] = e, h.topBorder && h.topBorder.attr(l), h.startSlice.attr(l), h.endSlice.attr(l), h.bottomBorder.attr(l);
						else {
							if (c !== "fill" && (c === "start" || c === "end")) o = !0
						} else g._attr(c, e);
						o && (J.updateSliceConf(k), J.refreshDrawing())
					}
					return g
				},
				d = function(a, b, d, c) {
					var e = this._confObject.elements,
						g;
					for (g in e) if (d) e[g].drag(b, d, c);
					else e[g].on(a, b);
					return d ? this.drag(b, d, c) : this._on(a, b)
				},
				c = function() {
					var a = this._confObject.elements,
						b;
					for (b in a) a[b].hide();
					return this._hide()
				},
				e = function() {
					var a = this._confObject.elements,
						b;
					for (b in a) a[b].show();
					return this._show()
				},
				k = function() {
					var a = this._confObject,
						b = a.elements,
						d;
					for (d in b) b[d].destroy();
					Z && (a.clipTop.destroy(), a.clipOuterFront.destroy(), a.clipOuterBack.destroy(), a.clipOuterFront1 && a.clipOuterFront1.destroy(), a.clipInnerFront && a.clipInnerFront.destroy(), a.clipInnerBack && a.clipInnerBack.destroy());
					return this._destroy()
				};
			return function(a, r, h, n, m, p, l, f, o) {
				var H = this.renderer,
					h = this.parseColor(h, n),
					i, a = {
						start: a,
						end: r,
						elements: {},
						pie3DManager: this
					},
					r = this.slicingWallsArr,
					n = a.elements,
					w, q = Z ? "litepath" : "path";
				this.updateSliceConf(a, !0);
				if (Z) {
					i = {
						fill: ba(h.top),
						"stroke-width": 0
					};
					if (o !== 1) i.stroke = m, i["stroke-width"] = p;
					i = H[q](a.clipTopPath, this.topGroup).attr(i);
					if (o) n.topBorder = H[q](a.clipTopBorderPath, this.topGroup).attr({
						fill: ba(h.topBorder),
						"stroke-width": 0
					})
				} else i = H[q](a.clipTopPath, this.topGroup).attr({
					fill: ba(h.top),
					"stroke-width": 0
				}), n.topBorder = H[q](a.clipTopBorderPath, this.topGroup).attr({
					stroke: m,
					"stroke-width": p
				});
				n.bottom = H[q](a.clipTopPath, this.bottomBorderGroup).attr({
					fill: ba(h.bottom),
					"stroke-width": 0
				});
				n.bottomBorder = H[q](Z ? a.clipTopPath : a.clipTopBorderPath, this.bottomBorderGroup).attr({
					stroke: m,
					"stroke-width": p
				});
				n.frontOuter = H[q](a.clipOuterFrontPath, this.slicingWallsFrontGroup).attr({
					fill: ba(h.frontOuter),
					"stroke-width": 0
				});
				n.backOuter = H[q](a.clipOuterBackPath, this.outerBackGroup).attr({
					fill: ba(h.backOuter),
					"stroke-width": 0
				});
				n.startSlice = H[q](a.startSlice, this.slicingWallsFrontGroup).attr({
					fill: ba(h.startSlice),
					stroke: m,
					"stroke-width": p
				});
				n.endSlice = H[q](a.endSlice, this.slicingWallsFrontGroup).attr({
					fill: ba(h.endSlice),
					stroke: m,
					"stroke-width": p
				});
				m = sa(a.start);
				p = sa(a.end);
				o = (m > p ? fa : 0) + p - m;
				if (o > W && (n.frontOuter1 = H[q](a.clipOuterFrontPath1, this.slicingWallsFrontGroup).attr({
					fill: ba(h.frontOuter),
					"stroke-width": 0
				}), n.frontOuter1._conf = {
					index: m,
					isStart: 0.5,
					pIndex: l
				}, Z)) a.clipOuterFront1 = a.clipOuterFrontPath1;
				n.frontOuter._conf = {
					index: Ea(p, m),
					isStart: 0.5,
					pIndex: l
				};
				n.startSlice._conf = {
					index: m,
					isStart: 0,
					pIndex: l
				};
				n.endSlice._conf = {
					index: p,
					isStart: 1,
					pIndex: l
				};
				this.hasOnePoint && (n.startSlice.hide(), n.endSlice.hide());
				this.isDoughnut ? (n.frontInner = H[q](a.clipInnerFrontPath, this.innerFrontGroup).attr({
					fill: ba(h.frontInner),
					"stroke-width": 0
				}), n.backInner = H[q](a.clipInnerBackPath, this.innerBackGroup).attr({
					fill: ba(h.backInner),
					"stroke-width": 0
				}), n.backInner._conf = {
					index: Ea(p, m),
					isStart: 0.5,
					pIndex: l
				}, o > W ? Z ? r.push(n.startSlice, n.frontOuter1, n.frontOuter, n.backInner, n.endSlice) : r.push(n.startSlice, n.frontOuter1, n.frontOuter, n.endSlice) : Z ? r.push(n.startSlice, n.frontOuter, n.backInner, n.endSlice) : r.push(n.startSlice, n.frontOuter, n.endSlice)) : o > W ? r.push(n.startSlice, n.frontOuter1, n.frontOuter, n.endSlice) : r.push(n.startSlice, n.frontOuter, n.endSlice);
				if (f !== void 0) {
					for (w in n) n[w].tooltip(f);
					i.tooltip(f)
				}
				if (Z && (a.clipTop = a.clipTopPath, a.clipOuterFront = a.clipOuterFrontPath, a.clipOuterBack = a.clipOuterBackPath, this.isDoughnut)) a.clipInnerFront = a.clipInnerFrontPath, a.clipInnerBack = a.clipInnerBackPath;
				i._confObject = a;
				a.thisElement = i;
				i._destroy = i.destroy;
				i.destroy = k;
				i._show = i.show;
				i.show = e;
				i._hide = i.hide;
				i.hide = c;
				i._on = i.on;
				i.on = d;
				i._attr = i.attr;
				i.attr = b;
				this.pointElemStore.push(i);
				return i
			}
		}()
	};
	Fa.prototype.constructor = Fa;
	o("renderer.pie3d", {
		type: "pie3d",
		drawCaption: function() {
			var g;
			var a = this.options.chart,
				b = this.options.title,
				d = this.options.subtitle,
				c = this.paper,
				e = this.elements,
				h = this.layers,
				j = h.caption,
				r = e.caption,
				k = e.subcaption,
				n = b && b.text,
				m = d && d.text,
				p = c.width / 2,
				l = b.x,
				f = d && d.x;
			if ((n || m) && !j) j = h.caption = c.group("caption"), h.tracker ? j.insertBefore(h.tracker) : j.insertAfter(h.dataset);
			if (n) {
				if (!r) r = e.caption = c.text(j);
				if (l === void 0) l = p, b.align = "middle";
				r.css(b.style).attr({
					text: b.text,
					fill: b.style.color,
					x: l,
					y: b.y || a.spacingTop || 0,
					"text-anchor": b.align || "middle",
					"vertical-align": "top",
					visibility: "visible",
					title: b.originalText || ""
				})
			} else if (r) g = e.caption = r.remove(), r = g;
			if (m) {
				if (!k) k = e.subcaption = c.text(j);
				if (f === void 0) f = p, d.align = "middle";
				k.css(d.style).attr({
					text: d.text,
					title: d.originalText || "",
					fill: d.style.color,
					x: f,
					y: n ? r.attrs.y + r.getBBox().height + 2 : b.y || a.spacingTop || 0,
					"text-anchor": d.align || "middle",
					"vertical-align": "top",
					visibility: "visible"
				})
			} else if (k) e.subcaption = k.remove();
			if (!n && !m && j) h.caption = j.remove()
		},
		translate: function() {
			var a = 0,
				b = this.options,
				d = b.series[0],
				c = b.plotOptions.series.dataLabels,
				e = b.plotOptions.pie3d,
				h = i(d.startAngle, 0) % 360,
				j = d.managedPieSliceDepth,
				r = d.slicedOffset = e.slicedOffset,
				k = this.canvasWidth,
				n = this.canvasHeight,
				m = [this.canvasLeft + k * 0.5, this.canvasTop + n * 0.5 - j * 0.5],
				p, l, o, u, y, b = d.data,
				X, C = ha(k, n),
				q, x, t, K = c.distance,
				M = d.pieYScale,
				F = d.pieSliceDepth,
				v = d.slicedOffsetY = r * M;
			m.push(e.size, e.innerSize || 0);
			m = ja(m, function(a, b) {
				return (q = /%$/.test(a)) ? [k, n - j, C, C][b] * parseInt(a, 10) / 100 : a
			});
			m[2] /= 2;
			m[3] /= 2;
			m.push(m[2] * M);
			m.push((m[2] + m[3]) / 2);
			m.push(m[5] * M);
			d.getX = function(a, b) {
				o = H.asin((a - m[1]) / (m[2] + K));
				return m[0] + (b ? -1 : 1) * w(o) * (m[2] + K)
			};
			d.center = m;
			qa(b, function(b) {
				a += b.y
			});
			d.labelsRadius = m[2] + K;
			d.labelsRadiusY = d.labelsRadius * M;
			d.quadrantHeight = (n - j) / 2;
			d.quadrantWidth = k / 2;
			u = -h * T;
			u = A(u * 1E3) / 1E3;
			y = u + fa;
			e = f(parseInt(c.style.fontSize, 10), 10) + 4;
			d.maxLabels = ia(d.quadrantHeight / e);
			d.labelFontSize = e;
			d.connectorPadding = f(c.connectorPadding, 5);
			d.isSmartLineSlanted = i(c.isSmartLineSlanted, !0);
			d.connectorWidth = f(c.connectorWidth, 1);
			d.enableSmartLabels = c.enableSmartLabels;
			if (!d.pie3DManager) d.pie3DManager = new Fa(m[0], m[1], m[2], m[3], M, F, this.layers.dataset, this.paper, d.data.length === 1, d.use3DLighting);
			qa(b, function(b) {
				p = u;
				X = a ? b.y / a : 0;
				u = A((u + X * fa) * 1E3) / 1E3;
				u > y && (u = y);
				l = u;
				b.shapeArgs = {
					start: A(p * 1E3) / 1E3,
					end: A(l * 1E3) / 1E3
				};
				b.centerAngle = o = (l + p) / 2 % fa;
				b.slicedTranslation = [A(w(o) * r), A(P(o) * v)];
				x = w(o) * m[2];
				d.radiusY = t = P(o) * m[4];
				b.tooltipPos = [m[0] + x * 0.7, m[1] + t];
				b.percentage = X * 100;
				b.total = a
			})
		},
		drawPlotPie3d: function(a, b) {
			this.translate();
			var d = this,
				c = a.items,
				e = a.data,
				h = d.options,
				j = h.plotOptions,
				k = j.series,
				o = d.layers,
				n = d.elements.plots[0],
				m = d.datasets[0],
				j = j.series.dataLabels,
				p = k.dataLabels.style,
				k = f(a.moveDuration, k.animation.duration),
				l = d.paper,
				h = (h = h.tooltip || {}) && h.enabled !== !1,
				i, H = m.slicedOffset,
				u = m.slicedOffsetY,
				A = d.plotGraphicClick,
				C = d.plotDragMove,
				q = d.plotDragStart,
				x = d.plotDragEnd,
				X = d.plotMouseDown,
				M = d.plotMouseUp,
				t = !! d.datasets[0].enableRotation,
				F = b.showBorderEffect,
				v = e.length,
				s, z, ha, K, D, L, G, I, Y, E, N;
			if (!e || !v) e = [];
			n.singletonCase = v == 1;
			n.chartPosition = Da(d.container);
			n.pieCenter = m.center;
			n.timerThreshold = 30;
			for (N = -1; ++N < v;) if (z = e[N], ha = z.y, K = z.displayValue, L = z.sliced, E = z.shapeArgs, I = z.centerAngle, i = z.toolText, G = (D = !! z.link) || t || !z.doNotSlice, !(ha === null || ha === void 0)) if (!(s = c[N])) {
				b.data[N].plot = s = c[N] = {
					chart: d,
					index: N,
					seriesData: n,
					value: ha,
					angle: I,
					link: z.link,
					shapeArgs: E,
					slicedX: L && !n.singletonCase ? w(I) * H : 0,
					slicedY: L && !n.singletonCase ? P(I) * u : 0,
					sliced: L,
					labelText: K,
					name: z.name,
					label: z.name,
					percentage: z.percentage,
					toolText: i,
					originalIndex: v - N - 1,
					graphic: m.pie3DManager.createSlice(E.start, E.end, z.color, z._3dAlpha, z.borderColor, z.borderWidth, N, h ? i : "", F)
				};
				b.data[N].legendClick = function(a) {
					return function() {
						d.legendClick(a, !0, !1)
					}
				}(s);
				s.graphic.plotItem = s;
				s.transX = w(I) * H;
				s.transY = P(I) * u;
				s.slicedTranslation = "t" + s.transX + "," + s.transY;
				s.graphic.attr({
					transform: "t" + s.slicedX + "," + s.slicedY,
					ishot: G,
					cursor: D ? "pointer" : ""
				});
				if (!z.doNotSlice) s.graphic.on("click", function(a) {
					return function() {
						A.call(a)
					}
				}(s));
				s.graphic.on("drag", function(a) {
					return function(b, d, c, e, g) {
						C.call(a, b, d, c, e, g)
					}
				}(s), function(a) {
					return function(b, d, c) {
						q.call(a, b, d, c)
					}
				}(s), function(a) {
					return function() {
						x.call(a)
					}
				}(s));
				s.graphic.on("mousedown", function(a) {
					return function() {
						X.call(a)
					}
				}(s));
				s.graphic.on("mouseup", function(a) {
					return function() {
						M.call(a)
					}
				}(s));
				if (K !== void 0 && (s.dataLabel = l.text(o.dataset).css(p).attr({
					text: K,
					title: z.originalText || "",
					fill: p.color || "#000000",
					visibility: "hidden",
					ishot: G,
					cursor: D ? "pointer" : ""
				}), s.dataLabel.click(A, s), s.dataLabel.mousedown(X, s), s.dataLabel.mouseup(M, s), j.distance > 0 && (Y = j.connectorWidth) && j.enableSmartLabels)) s.connector = l.path("M 0 0 l 0 0", o.dataset).attr({
					"stroke-width": Y,
					stroke: j.connectorColor || "#606060",
					visibility: "hidden",
					ishot: G,
					cursor: D ? "pointer" : ""
				}), s.connector.click(A, s), s.connector.mousedown(X, s), s.connector.mouseup(M, s)
			}
			m.pie3DManager.refreshDrawing();
			k > 0 ? d.animate(c, k) : d.placeDataLabels(!1, c)
		},
		rotate: function(a) {
			var b = this.datasets[0],
				d = this.elements.plots[0].items,
				c = b.slicedOffset,
				e = b.slicedOffsetY,
				h = b.startAngle,
				j, a = a || -b._lastAngle;
			j = (a - h) % 360;
			b.startAngle = f(a, b.startAngle) % 360;
			j = -(j * u) / 180;
			b.pie3DManager && b.pie3DManager.rotate(j);
			qa(d, function(a) {
				var b = a.graphic,
					d = a.shapeArgs,
					d = {
						start: d.start += j,
						end: d.end += j
					},
					h = a.angle = sa((d.start + d.end) / 2),
					d = a.sliced,
					k = w(h),
					h = P(h);
				a.slicedTranslation = [A(k * c), A(h * e)];
				a.transX = a.slicedTranslation[0];
				a.transY = a.slicedTranslation[1];
				a.slicedX = d ? w(j) * c : 0;
				a.slicedY = d ? P(j) * e : 0;
				b && d && a.graphic.attr({
					transform: "t" + a.slicedTranslation[0] + "," + a.slicedTranslation[1]
				})
			});
			this.placeDataLabels(!0, d)
		},
		plotMouseDown: function() {
			this.seriesData.isRotating = !1
		},
		plotMouseUp: function() {
			var a = this.chart,
				b = this.seriesData;
			!b.isRotating && a.linkClickFN.call({
				link: b.data[this.index].link
			}, a);
			ga._supportsTouch && !b.isRotating && a.plotGraphicClick.call(this)
		},
		plotDragStart: function(a, b, d) {
			var c = this.seriesData,
				e = this.chart.datasets[0];
			if (e.enableRotation) a = Ja.call(d, a, b, c.pieCenter, c.chartPosition, e.pieYScale), e.dragStartAngle = a, e._lastAngle = -e.startAngle
		},
		plotDragEnd: function() {
			var a = this,
				b = a.chart,
				d = {
					hcJSON: {
						series: [{
							startAngle: b.datasets[0].startAngle
						}]
					}
				};
			b.disposed || xa(b.logic.chartInstance.jsVars._reflowData, d, !0);
			setTimeout(function() {
				a.seriesData.isRotating = !1
			}, 0)
		},
		plotDragMove: function(a, b, d, c, e) {
			var h = this.chart,
				a = h.datasets[0],
				b = this.seriesData;
			if (h.options.series[0].enableRotation && !b.singletonCase && (b.isRotating = !0, d = Ja.call(e, d, c, b.pieCenter, b.chartPosition, a.pieYScale), c = d - a.dragStartAngle, a.dragStartAngle = d, b.moveDuration = 0, a._lastAngle += c * 180 / u, d = (new Date).getTime(), !a._lastTime || a._lastTime + b.timerThreshold < d)) a._lastTime || h.rotate(), b.timerId = setTimeout(function() {
				(!h.disposed || !h.disposing) && h.rotate()
			}, b.timerThreshold), a._lastTime = d
		},
		animate: function(a, b) {
			var d, c, e, h = a.length,
				j, k, f, n = this,
				m;
			if (n.datasets[0].alphaAnimation) n.layers.dataset.attr({
				opacity: 0
			}), n.layers.dataset.animate({
				opacity: 1
			}, b, "ease-in", function() {
				!n.disposed && !n.disposing && n.placeDataLabels(!1, a)
			});
			else for (d = 0; d < h; d++) if (j = a[d], k = j.graphic, f = j.shapeArgs, j = 2 * u, k) k.attr({
				start: j,
				end: j
			}), m = f.start, f = f.end, c ? k.animateWith(c, e, {
				cx: m - j,
				cy: f - j
			}, b, "ease-in") : (e = ga.animation({
				cx: m - j,
				cy: f - j
			}, b, "ease-in", function() {
				!n.disposed && !n.disposing && n.placeDataLabels(!1, a)
			}), c = k.animate(e))
		},
		plotGraphicClick: function() {
			var a = this.seriesData,
				b = this.chart,
				c, e, h, k, j, f;
			if (!a.isRotating && !a.singletonCase) return c = this.graphic, e = this.connector, h = this.dataLabel, a = this.sliced, k = this.connectorPath, j = (a ? -1 : 1) * this.transX, f = (a ? -1 : 1) * this.transY, c.animate({
				transform: a ? "t0,0" : "t" + j + "," + f
			}, 200, "easeIn"), h && h.x && h.animate({
				x: h.x + (a ? 0 : j)
			}, 200, "easeIn"), k && (k[1] += j, k[2] += f, k[4] += j, k[6] += j, e.animate({
				path: k
			}, 200, "easeIn")), a = this.sliced = !a, c = {
				hcJSON: {
					series: []
				}
			}, c.hcJSON.series[0] = {
				data: []
			}, xa(b.logic.chartInstance.jsVars._reflowData, c, !0), a
		},
		placeDataLabels: function() {
			var a = function(a, b) {
					return a.point.value - b.point.value
				},
				e = function(a, b) {
					return a.angle - b.angle
				},
				d = ["start", "start", "end", "end"],
				g = [-1, 1, 1, -1],
				h = [1, 1, -1, -1];
			return function(o, j) {
				var r = this.datasets[0],
					i = this.smartLabel,
					n = this.options.plotOptions.series.dataLabels,
					m = n.style,
					p = f(ta(parseFloat(m.lineHeight)), 12),
					l = ka(n.placeInside, !1),
					u = n.skipOverlapLabels,
					X = n.manageLabelOverflow,
					y = n.connectorPadding,
					K = n.distance;
				ka(n.softConnector, !0);
				var C = n.connectorWidth,
					q, x, K = K > 0,
					t = r.center,
					E = t[1],
					T = t[0],
					F = t[2],
					v = t[4],
					s = [
						[],
						[],
						[],
						[]
					],
					z, V, Q, D = this.canvasLeft,
					L = this.canvasTop,
					G = this.canvasWidth,
					I, R, B, N, da, ia, ca, O, ba, Z, oa, Ha = r.labelsRadius,
					ea = A(r.labelsRadiusY * 100) / 100,
					ga = r.labelFontSize,
					U = ga,
					Ca = U / 2,
					y = [y, y, -y, -y],
					ja = r.maxLabels,
					Ra = r.isSmartLineSlanted,
					na = r.enableSmartLabels,
					pa, r = r.pieSliceDepth / 2;
				o || i.setStyle(m);
				if (j.length == 1) {
					if (N = j[0], pa = N.dataLabel, N.slicedTranslation = [D, L], pa) pa.attr({
						visibility: b,
						"text-anchor": "middle",
						x: T,
						y: E + Ca - 2
					}), pa.x = T
				} else if (l) qa(j, function(a) {
					if (pa = a.dataLabel) {
						oa = a.angle;
						Z = E + t[6] * P(oa) + Ca - 2;
						ca = T + t[5] * w(oa);
						pa.x = ca;
						pa._x = ca;
						pa.y = Z;
						if (a.sliced) {
							var a = a.slicedTranslation,
								c = a[1] - L;
							ca += a[0] - D;
							Z += c
						}
						pa.attr({
							visibility: b,
							align: "middle",
							x: ca,
							y: Z
						})
					}
				});
				else {
					qa(j, function(a) {
						if (pa = a.dataLabel) oa = a.angle, oa < 0 && (oa = fa + oa), z = oa >= 0 && oa < S ? 1 : oa < W ? 2 : oa < Ua ? 3 : 0, s[z].push({
							point: a,
							angle: oa
						})
					});
					for (Q = l = 4; Q--;) {
						if (u && (N = s[Q].length - ja, N > 0)) {
							s[Q].sort(a);
							V = s[Q].splice(0, N);
							R = 0;
							for (B = V.length; R < B; R += 1) N = V[R].point, N.dataLabel.attr({
								visibility: "hidden"
							}), N.connector && N.connector.attr({
								visibility: "hidden"
							})
						}
						s[Q].sort(e)
					}
					Q = Y(s[0].length, s[1].length, s[2].length, s[3].length);
					ea = Y(ha(Q, ja) * U, ea + U);
					s[1].reverse();
					s[3].reverse();
					for (i.setStyle(m); l--;) {
						R = s[l];
						B = R.length;
						u || (U = B > ja ? ea / B : ga, Ca = U / 2);
						N = B * U;
						m = ea;
						for (Q = 0; Q < B; Q += 1, N -= U) x = M(ea * P(R[Q].angle)), m < x ? x = m : x < N && (x = N), m = (R[Q].oriY = x) - U;
						V = d[l];
						B = ea - (B - 1) * U;
						m = 0;
						for (Q = R.length - 1; Q >= 0; Q -= 1, B += U) {
							N = R[Q].point;
							oa = R[Q].angle;
							da = N.sliced;
							pa = N.dataLabel;
							x = M(ea * P(oa));
							x < m ? x = m : x > B && (x = B);
							m = x + U;
							O = (x + R[Q].oriY) / 2;
							x = T + h[l] * Ha * w(H.asin(O / ea));
							O *= g[l];
							O += E;
							ba = E + v * P(oa);
							ia = T + F * w(oa);
							(l < 2 && x < ia || l > 1 && x > ia) && (x = ia);
							ca = x + y[l];
							Z = O + Ca - 2;
							q = ca + y[l];
							pa.x = q;
							pa._x = q;
							X && (I = l > 1 ? q - this.canvasLeft : this.canvasLeft + G - q, I = i.getSmartText(N.labelText, I, p), pa.attr({
								text: I.text,
								title: I.tooltext || ""
							}));
							oa < W && (O += r, ba += r, Z += r);
							pa.y = Z;
							if (da) da = N.transX, I = N.transY, ca += da, x += da, ia += da, ba += I, q += da;
							pa.attr({
								visibility: b,
								"text-anchor": V,
								x: q,
								y: O
							});
							if (K && C && na) q = N.connector, N.connectorPath = x = [c, ia, ba, k, Ra ? x : ia, O, ca, O], q ? (q.attr({
								path: x
							}), q.attr("visibility", b)) : N.connector = q = this.paper.path(x).attr({
								"stroke-width": C,
								stroke: n.connectorColor || "#606060",
								visibility: b
							})
						}
					}
				}
			}
		}(),
		legendClick: function(a, b, c) {
			var e = a.chart;
			a.chart.elements.plots[0].isRotating = !1;
			e.plotGraphicClick.call(a);
			c !== !0 && (eventArgs = {
				datasetName: a.label,
				datasetIndex: a.originalIndex,
				id: a.userID,
				visible: b,
				label: a.label,
				value: a.value,
				percentValue: a.percentage,
				tooltext: a.toolText,
				link: a.link,
				sliced: !a.sliced
			}, ma.raiseEvent("legenditemclicked", eventArgs, e.logic.chartInstance))
		}
	}, o["renderer.root"]);
	o("renderer.pie", {
		drawPlotPie: function(a, c) {
			var d = this,
				e = a.items,
				h = a.data,
				k = d.options,
				j = k.plotOptions,
				r = j.pie,
				o = j.series,
				n = d.layers,
				m = n.dataset,
				p = d.elements.plots[0],
				j = j.series.dataLabels,
				l = o.dataLabels.style,
				i = o.shadow,
				o = f(a.moveDuration, o.animation.duration),
				H = d.paper,
				k = (k = k.tooltip || {}) && k.enabled !== !1,
				u = ((c.startAngle *= -W / 180) || 0) % fa,
				A = r.slicedOffset,
				C = c.valueTotal,
				q = fa / C,
				x = d.canvasLeft + d.canvasWidth * 0.5,
				X = d.canvasTop + d.canvasHeight * 0.5,
				t = r.size * 0.5,
				K = (r.innerSize || 0) * 0.5,
				F = d.plotGraphicClick,
				v = d.plotDragMove,
				s = d.plotDragStart,
				z = d.plotDragEnd,
				ha = d.plotMouseDown,
				M = d.plotMouseUp,
				D = !! d.datasets[0].enableRotation,
				L = h.length,
				G, I, Y, E, N, S, T, R, B, V, ia, ca = a.shadowGroup,
				ta, O, U, Z, ea;
			if (!h || !L) h = [];
			if (!ca) ca = a.shadowGroup = H.group(m).toBack();
			p.singletonCase = L == 1;
			p.chartPosition || (p.chartPosition = Da(d.container));
			p.pieCenter = [x, X];
			p.timerThreshold = 30;
			V = B = u;
			for (ta = L; ta--;) if (I = h[ta], Y = I.y, E = I.displayValue, S = I.sliced, r = I.toolText, T = (N = !! I.link) || D || !I.doNotSlice, !(Y === null || Y === void 0)) {
				G = I.color.FCcolor;
				G.r = t;
				G.cx = x;
				G.cy = X;
				V = B;
				B -= !p.singletonCase ? Y * q : fa;
				R = (B + V) * 0.5;
				o ? Z = ea = u : (Z = B, ea = V);
				if (!(G = e[ta])) if (c.data[ta].plot = G = e[ta] = {
					chart: d,
					index: ta,
					seriesData: p,
					value: Y,
					angle: R,
					slicedX: w(R) * A,
					slicedY: P(R) * A,
					sliced: S,
					labelText: E,
					toolText: r,
					label: I.name,
					link: I.link,
					percentage: C ? Y * C / 100 : 0,
					originalIndex: L - ta - 1,
					graphic: H.ringpath(x, X, t, K, Z, ea, n.dataset).attr({
						"stroke-width": I.borderWidth,
						"stroke-linejoin": "round",
						stroke: I.borderColor,
						fill: ba(I.color),
						"stroke-dasharray": I.dashStyle,
						redrawDataLabels: u,
						ishot: T,
						cursor: N ? "pointer" : ""
					}).shadow(i && I.shadow, ca).drag(v, s, z).mousedown(ha).mouseup(M)
				}, I.doNotSlice || G.graphic.click(F), k && G.graphic.tooltip(r), c.data[ta].legendClick = function(a) {
					return function() {
						d.legendClick(a, !0, !1)
					}
				}(G), G.graphic.data("plotItem", G), E !== void 0 && (G.dataLabel = H.text(m).css(l).attr({
					text: E,
					fill: l.color || "#000000",
					ishot: T
				}).click(F).drag(v, s, z).mousedown(ha).mouseup(M).hide(), G.dataLabel.data("plotItem", G), j.distance > 0 && (ia = j.connectorWidth) && j.enableSmartLabels)) G.connector = H.path("M 0 0 l 0 0", m).attr({
					"stroke-width": ia,
					stroke: j.connectorColor || "#606060",
					visibility: b,
					ishot: !0
				}).click(F).drag(v, s, z).mousedown(ha).mouseup(M), G.connector.data("plotItem", G);
				G.angle = R;
				G.transX = w(R) * A;
				G.transY = P(R) * A;
				G.slicedTranslation = "t" + w(R) * A + "," + P(R) * A;
				o ? O ? G.graphic.animateWith(O, U, {
					ringpath: [x, X, t, K, B, V],
					transform: G.sliced ? G.slicedTranslation : ""
				}, o, "easeIn") : (U = ga.animation({
					ringpath: [x, X, t, K, B, V],
					redrawDataLabels: d,
					transform: G.sliced ? G.slicedTranslation : ""
				}, o, "easeIn", function() {
					if (!d.disposed && !d.disposing && !d.paper.ca.redrawDataLabels) d.placeDataLabels(!1, e, a), d.paper.ca.redrawDataLabels = d.redrawDataLabels
				}), O = G.graphic.animate(U)) : G.graphic.attr({
					transform: G.sliced ? G.slicedTranslation : ""
				})
			}
			o || d.placeDataLabels(!1, e, a)
		},
		rotate: function(a, b) {
			var c = a.items,
				e = a.data,
				h = this.options.plotOptions.pie,
				k = h.slicedOffset,
				j = fa / b.valueTotal,
				f = this.canvasLeft + this.canvasWidth * 0.5,
				o = this.canvasTop + this.canvasHeight * 0.5,
				n = h.size * 0.5,
				h = (h.innerSize || 0) * 0.5,
				m, p, l, i, H;
			l = (b.startAngle || 0) % fa;
			for (H = e.length; H--;) if (m = e[H], p = m.y, !(p === null || p === void 0)) m = c[H], i = l, l -= !m.seriesData.singletonCase ? p * j : fa, p = (l + i) * 0.5, m.angle = p, m.transX = w(p) * k, m.transY = P(p) * k, m.slicedTranslation = "t" + w(p) * k + "," + P(p) * k, m.graphic.attr({
				ringpath: [f, o, n, h, l, i],
				transform: m.sliced ? m.slicedTranslation : ""
			});
			this.placeDataLabels(!0, c, a)
		}
	}, o["renderer.piebase"])
}, [3, 2, 2, "sr4"]]);
FusionCharts(["private", "modules.renderer.js-zoomline", function() {
	var Da = this,
		ja = Da.hcLib,
		sa = window,
		Ea = /msie/i.test(navigator.userAgent) && !sa.opera,
		Ja = ja.chartAPI,
		Fa = ja.chartAPI,
		ma = ja.extend2,
		t = ja.raiseEvent,
		ga = ja.pluck,
		E = ja.pluckNumber,
		B = ja.getFirstColor,
		Ka = ja.graphics.convertColor,
		i = ja.defaultPaletteOptions,
		va = ja.bindSelectionEvent,
		f = ja.createTrendLine,
		U = ja.parseUnsafeString,
		ka = ja.Raphael,
		Oa = ja.FC_CONFIG_STRING,
		wa = "rgba(192,192,192," + (Ea ? 0.002 : 1.0E-6) + ")",
		O = Math,
		xa = O.ceil,
		za = O.floor,
		ba = O.max,
		Qa = O.min,
		Ga = O.cos,
		Z = O.sin,
		Aa = parseFloat,
		qa = parseInt,
		La = function(b) {
			return b && b.replace(/[-[\]{}()*+?.,\\^$|#\s]/g, "\\$&")
		},
		la;
	ma(ja.eventList, {
		zoomed: "FC_Zoomed",
		pinned: "FC_Pinned",
		resetzoomchart: "FC_ResetZoomChart"
	});
	Ja("zoomline", {
		friendlyName: "Zoomable and Panable Multi-series Line Chart",
		rendererId: "zoomline",
		standaloneInit: !0,
		hasVDivLine: !0,
		defaultSeriesType: "stepzoom",
		canvasborderthickness: 1,
		defaultPlotShadow: 1,
		chart: function() {
			var b = this.base.chart.apply(this, arguments),
				c = this.dataObj.chart,
				k = i.canvasBorderColor[this.paletteIndex],
				e = b.chart;
			ma(e, {
				animation: !1,
				zoomType: "x",
				canvasPadding: E(c.canvaspadding, 0),
				scrollColor: B(ga(c.scrollcolor, i.altHGridColor[e.paletteIndex])),
				scrollBtnWidth: E(c.scrollbtnwidth, c.scrollheight, 16),
				scrollHeight: E(c.scrollheight, 16) || 16,
				allowPinMode: E(c.allowpinmode, 1),
				skipOverlapPoints: E(c.skipoverlappoints, 1),
				showToolBarButtonTooltext: E(c.showtoolbarbuttontooltext, 1),
				btnResetChartTooltext: ga(c.btnresetcharttooltext, "Reset Chart"),
				btnZoomOutTooltext: ga(c.btnzoomouttooltext, "Zoom out one level"),
				btnSwitchToZoomModeTooltext: ga(c.btnswitchtozoommodetooltext, "<strong>Switch to Zoom Mode</strong><br/>Select a subset of data to zoom into it for detailed view"),
				btnSwitchToPinModeTooltext: ga(c.btnswitchtopinmodetooltext, "<strong>Switch to Pin Mode</strong><br/>Select a subset of data and compare with the rest of the view"),
				pinPaneFill: Ka(ga(c.pinpanebgcolor, k), E(c.pinpanebgalpha, 15)),
				zoomPaneFill: Ka(ga(c.zoompanebgcolor, "#b9d5f1"), E(c.zoompanebgalpha, 30)),
				zoomPaneStroke: Ka(ga(c.zoompanebordercolor, "#3399ff"), E(c.zoompaneborderalpha, 80))
			});
			return b
		},
		preSeriesAddition: function() {
			var b = this.dataObj,
				c = b.chart,
				k = (k = b.categories) && k[0] || {},
				b = k.category,
				e = this.hcJSON,
				h = e[Oa],
				f = this.smartLabel,
				i = E(c.compactdatamode, 0),
				w = ga(c.dataseparator, "|"),
				X = E(c.showlabels, 1),
				A = c.labeldisplay && c.labeldisplay.toLowerCase(),
				t = X && E(c.labelheight),
				Y = A === "rotate" ? 270 : E(c.rotatelabels, 1) ? 270 : 0,
				M = e.xAxis.labels.style,
				u = Aa(M.lineHeight),
				B = e.chart.labelPadding = E(c.labelpadding, 2) + e.chart.plotBorderWidth,
				ia, R = 0,
				T = -1,
				W, S, fa;
			delete k.category;
			e.categories = A = ma({
				data: w = i && b && b.split && b.split(w) || b || [],
				rotate: Y,
				wrap: A !== "none"
			}, k);
			b !== void 0 && (k.category = b);
			k = w.length || 0;
			if (W = !i && X && t !== 0 && k || 0) {
				for (; W--;) w[W] = w[W] && (ia = w[W].label || "") && ((S = ia.length) > R && (R = S, T = W, ia) || ia) || "";
				R && (ia = w[T])
			} else if (i && k) if (Y) {
				S = sa.document.createElement("div");
				t = sa.document.createElement("span");
				S.setAttribute("class", "fusioncharts-zoomline-localsmartlabel");
				S.style.cssText = "display:block;width:1px;position:absolute;";
				for (fa in M) S.style[fa] = M[fa];
				t.appendChild(sa.document.createTextNode(b.replace(/\s*/g, "").replace(/\|/g, " ")));
				S.appendChild(t);
				sa.document.body.appendChild(S);
				t = t.offsetWidth || void 0;
				S.parentNode.removeChild(S)
			} else ia = w[k - 1] || w[0];
			if ((t === void 0 || t === 0) && X) ia ? (f.setStyle(M), ia = f.getSmartText(ia), t = Y ? ia.width : ia.height) : t = u * (Y && 3 || 1);
			t > h.height * 0.3 && (t = h.height * 0.3);
			A.labelHeight = t && t + 10 || 0;
			A.show = t && X || 0;
			A.css = ma({}, M);
			Y ? (A.css.rotation = Y, A.css["text-anchor"] = "end") : A.css["vertical-align"] = "top";
			e.xAxis.min = 0;
			e.xAxis.max = k && k - 1 || 0;
			t += E(c.scrollheight, 16) || 16;
			e.chart.marginBottom += B;
			h.marginBottomExtraSpace += t + 5;
			ga(c.caption, c.subcaption) || (h.marginTopExtraSpace += 16)
		},
		series: function() {
			var b = this.dataObj,
				c = b.chart,
				k = b.dataset,
				e = this.hcJSON,
				h = e[Oa],
				i = h[0],
				P = e.series,
				w = E(c.yaxismaxvalue),
				t = E(c.yaxisminvalue),
				A = E(c.forceyaxislimits, 0),
				ha = E(c.compactdatamode, 0),
				Y = ga(c.dataseparator, "|"),
				M = La(c.indecimalseparator),
				u = La(c.inthousandseparator),
				B = E(c.drawanchors, c.showanchors, 1),
				ia = !! E(c.showlegend, 1),
				R, T, W, S, fa, O = Infinity,
				U = -Infinity,
				V;
			fa = e.categories.data.length;
			if (k && k.length && fa) {
				M && (M = RegExp(M, "g"));
				u && (u = RegExp(u, "g"));
				!u && !M && ha && A && w !== void 0 && t !== void 0 ? (A = !0, U = ba(w, t), O = Qa(t, w)) : A = !1;
				w = 0;
				for (t = k.length; w < t; w++) {
					R = k[w];
					W = R.data;
					delete R.data;
					ha ? (S = W || "", u && (S = S.replace(u, "")), M && (S = S.replace(M, ".")), S = S.split(Y)) : S = W || [];
					if (S.length > fa) S.length = fa;
					V = S.length;
					if (ha) {
						if (!A) for (; V--;) T = Aa(S[V]), isNaN(T) && (T = void 0), T > U && (U = T), T <= O && (O = T), S[V] = T
					} else for (; V--;) T = S[V] && S[V].value || "", u && (T = T.replace(u, "")), M && (T = T.replace(M, ".")), T = Aa(T), isNaN(T) && (T = void 0), T > U && (U = T), T <= O && (O = T), S[V] = T;
					P.push(T = {
						index: w,
						type: "zoomline",
						data: S,
						name: R.seriesname || "",
						showInLegend: R.seriesname && E(R.includeinlegend, 1) && ia || !1,
						showAnchors: E(R.drawanchors, R.showanchors, B),
						visible: !! E(R.initiallyvisible, 1),
						lineWidth: 2
					});
					S.length = fa;
					W !== void 0 && (R.data = W);
					T.attrs = this.seriesGraphicsAttrs(R, w);
					R = T.attrs.anchors;
					T.color = T.attrs.graphics.stroke;
					T.ancorRadius = R.r + R["stroke-width"] / 2;
					T.marker = {
						fillColor: R.fill,
						lineColor: R.stroke,
						lineWidth: 1,
						symbol: "circle"
					}
				}(U === -Infinity || O === Infinity) && (U = O = void 0);
				A = qa(E(c.displaystartindex, 1), 10) - 1;
				Y = qa(E(c.displayendindex, fa || 2), 10) - 1;
				(k = E(c.pixelsperpoint, 15)) < 5 && (k = 5);
				(P = E(c.pixelsperlabel, e.categories.rotate ? 20 : 60)) < k && (P = k);
				(A < 0 || A >= (fa - 1 || 1)) && (A = 0);
				(Y <= A || Y > (fa - 1 || 1)) && (Y = fa - 1 || 1);
				e.stepZoom = {
					cnd: E(c.connectnulldata, 0),
					amrd: E(c.anchorminrenderdistance, 20),
					nvl: E(c.numvisiblelabels, 0),
					cdm: ha,
					oppp: k,
					oppl: P,
					dsi: A,
					dei: Y,
					vdl: Y - A,
					dmax: i.max = U,
					dmin: i.min = O,
					clen: fa,
					offset: 0,
					step: 1,
					llen: 0,
					alen: 0,
					ddsi: A,
					ddei: Y,
					ppc: 0
				};
				e.crossline = {
					style: {
						lineHeight: h.inCanvasStyle.lineHeight,
						fontSize: h.inCanvasStyle.fontSize,
						fontFamily: h.inCanvasStyle.fontFamily
					}
				};
				this.configureAxis(e, b);
				b.trendlines && f(b.trendlines, e.yAxis, h, !1, this.isBar)
			}
		},
		seriesGraphicsAttrs: function(b, c) {
			var k = this.dataObj.chart,
				e = this.hcJSON.colors,
				h = (b.dashed || k.linedashed || "0") != 0,
				f, e = {
					"stroke-width": E(b.linethickness, k.linethickness, 2),
					stroke: B(ga(b.color, k.linecolor, e[c % e.length])),
					"stroke-opacity": E(b.alpha, k.linealpha, 100) / 100,
					"stroke-dasharray": h ? [E(b.linedashlen, k.linedashlen, 5), E(b.linedashgap, k.linedashgap, 4)] : "none",
					"stroke-linejoin": "round",
					"stroke-linecap": "round"
				},
				h = ma({}, e);
			f = e["stroke-width"] + E(k.pinlinethicknessdelta, 1);
			h["stroke-width"] = f > 0 && f || 0;
			h["stroke-dasharray"] = [3, 2];
			return {
				graphics: e,
				pin: h,
				shadow: {
					opacity: e["stroke-opacity"],
					apply: E(k.showshadow, +!ka.vml)
				},
				anchors: {
					"stroke-linejoin": "round",
					"stroke-linecap": "round",
					r: E(b.anchorradius, k.anchorradius, e["stroke-width"] + 2),
					stroke: B(ga(b.anchorbordercolor, k.anchorbordercolor, e.stroke)),
					"stroke-opacity": E(b.anchorborderalpha, k.anchorborderalpha, 100) / 100,
					"stroke-width": E(b.anchorborderthickness, k.anchorborderthickness, e["stroke-width"]),
					fill: B(ga(b.anchorbgcolor, k.anchorbgcolor, "#ffffff")),
					"fill-opacity": E(b.anchorbgalpha, k.anchorbgalpha, 100) / 100,
					opacity: E(b.anchoralpha, k.anchoralpha, 100) / 100
				},
				anchorShadow: E(k.anchorshadow, k.showshadow, +!ka.vml) && {
					apply: !0,
					opacity: E(b.anchoralpha, k.anchoralpha, 100) / 100
				}
			}
		},
		eiMethods: {
			zoomOut: function() {
				var b = this.jsVars,
					c;
				if (b && (c = b.hcObj)) return c.zoomOut && b.hcObj.zoomOut()
			},
			zoomTo: function(b, c) {
				var k = this.jsVars,
					e;
				if (k && (e = k.hcObj)) return e.zoomRange && k.hcObj.zoomRange(b, c - 2)
			},
			resetChart: function() {
				var b = this.jsVars,
					c;
				if (b && (c = b.hcObj)) c.pinRangePixels && b.hcObj.pinRangePixels(), c.resetZoom && b.hcObj.resetZoom()
			},
			setZoomMode: function(b) {
				var c = this.jsVars,
					k;
				c && (k = c.hcObj) && b && k.pinRangePixels && c.hcObj.pinRangePixels()
			},
			getViewStartIndex: function() {
				var b = this.jsVars,
					c;
				if (b && b.hcObj && (c = b.hcObj._zoominfo)) return c.ddsi + 1
			},
			getViewEndIndex: function() {
				var b = this.jsVars,
					c;
				if (b && b.hcObj && (c = b.hcObj._zoominfo)) return b = c.ddei - 1, b > c.clen ? c.clen : b
			}
		}
	}, Ja.msline);
	Fa("renderer.zoomline", {
		resetZoom: function() {
			var b = this._zoomhistory,
				c = this.options.stepZoom;
			if (!b.length) return !1;
			b.length = 0;
			this.zoomTo(c.dsi, c.dei) && t("resetzoomchart", this._zoomargs, this.fusionCharts, [this.fusionCharts.id]);
			return !0
		},
		zoomOut: function() {
			var b = this._zoomhistory.pop(),
				c = this.options.stepZoom,
				k, e, h;
			b ? (k = b.dsi, e = b.dei) : this._prezoomed && (k = 0, e = c.clen - 1);
			(h = this.zoomTo(k, e)) && Da.raiseEvent("zoomedout", h, this.fusionCharts);
			return !0
		},
		zoomRangePixels: function(b, c) {
			var k = this._zoomhistory,
				e = this._zoominfo,
				h = e.ppp,
				e = e.ddsi,
				f;
			k.push(this._zoominfo);
			(f = this.zoomTo(e + za(b / h), e + za(c / h))) ? Da.raiseEvent("zoomedin", f, this.fusionCharts) : k.pop()
		},
		zoomRange: function(b, c) {
			var k = this._zoomhistory,
				e;
			k.push(this._zoominfo);
			(e = this.zoomTo(+b - 1, +c + 1)) ? Da.raiseEvent("zoomedin", e, this.fusionCharts) : k.pop()
		},
		zoomTo: function(b, c) {
			var k = this.xlabels.data,
				e = this._zoominfo,
				h = this._zoomhistory,
				f = e.clen;
			b < 0 && (b = 0);
			b >= f - 1 && (b = f - 1);
			c <= b && (c = b + 1);
			c > f - 1 && (c = f - 1);
			if (b === c || b === e.dsi && c === e.dei) return !1;
			this.pinRangePixels();
			e = ma({}, e);
			e.dsi = b;
			e.dei = c;
			e = this._zoominfo = e;
			this.updatePlotZoomline();
			this.zoomOutButton[e.vdl === e.clen - 1 ? "hide" : "show"]();
			this.resetButton[h.length ? "show" : "hide"]();
			this.elements.zoomscroller.attr({
				"scroll-ratio": e.vdl / (f - !! f),
				"scroll-position": [e.dsi / (f - e.vdl - 1), !0]
			});
			k = {
				level: h.length + 1,
				startIndex: b,
				startLabel: k[b],
				endIndex: c,
				endLabel: k[c]
			};
			t("zoomed", k, this.fusionCharts, [this.fusionCharts.id, b, c, k.startLabel, k.endLabel, k.level]);
			return k
		},
		activatePin: function(b) {
			var c = this._zoominfo,
				k = this.options.chart,
				e = this.pinButton;
			if (e && c.pinned ^ (b = !! b)) return b || this.pinRangePixels(), k.showToolBarButtonTooltext && e.tooltip(k[b && "btnSwitchToZoomModeTooltext" || "btnSwitchToPinModeTooltext"] || ""), e.attr("button-active", b), c.pinned = b
		},
		pinRangePixels: function(b, c) {
			var k = this.paper,
				e = this.elements,
				h = this.xlabels.data,
				f = this._zoominfo,
				i = this.layers.zoompin,
				w = e.pinrect,
				X = e["clip-pinrect"],
				A = this._pingrouptransform,
				E = this.plots,
				Y = c - b,
				M, u;
			if (f && i && w) {
				if (b === c) return i.hide(), e.pintracker.hide(), this.pinButton.attr("button-active", !1), f.pinned = !1;
				for (u = E.length; u--;) {
					w = E[u];
					M = w.pinline;
					if (!M) M = w.pinline = k.path(void 0, i).attr(w.attrPin);
					M.attr("path", w.graphic.attrs.path)
				}
				X[0] = b + (ka.svg ? this.canvasLeft : 0);
				X[2] = Y;
				i.attr({
					"clip-rect": X,
					transform: A
				}).show();
				e.pintracker.__pindragdelta = 0;
				e.pintracker.show().attr({
					transform: A,
					x: b,
					width: Y
				});
				b = this.getValuePixel(b);
				c = this.getValuePixel(c);
				t("pinned", {
					startIndex: b,
					endIndex: c,
					startLabel: h[b],
					endLabel: h[c]
				}, this.fusionCharts, [this.fusionCharts.id, b, c, h[b], h[c]]);
				return f.pinned = !0
			}
		},
		getValuePixel: function(b) {
			var c = this._zoominfo;
			return c.ddsi + za(b / c.ppp)
		},
		getParsedLabel: function(b) {
			var c = this.xlabels;
			return c.parsed[b] || (c.parsed[b] = U(c.data[b] || ""))
		},
		drawGraph: function() {
			var b = this,
				c = b.paper,
				k = b.canvasLeft,
				e = b.canvasTop,
				h = b.canvasWidth,
				f = b.canvasHeight,
				i = b.options,
				w = i.chart,
				t = w.plotBorderWidth,
				A = w.useRoundEdges,
				E = w.showToolBarButtonTooltext,
				Y = b.layers,
				M = b.toolbar,
				u = b.elements,
				B = w.allowPinMode,
				O, R = i.categories,
				T = !1,
				W, S, U, Z, ba, V;
			V = b._zoominfo = ma({}, i.stepZoom);
			b._zoomhistory = [];
			if (V.clen) {
				T = b._prezoomed = V.dei - V.dsi < V.clen - 1;
				ba = b._visw = b.canvasWidth - w.canvasPadding * 2;
				Z = b._visx = b.canvasLeft + w.canvasPadding;
				b._visout = -(b.chartHeight + b.canvasHeight + 1E3);
				b.base.drawGraph.apply(b, arguments);
				b._ypvr = b.yAxis[0] && b.yAxis[0].pixelValueRatio || 0;
				O = b._yzero || 0;
				i = Y.dataset.attr("clip-rect", [b._visx, b.canvasTop, b._visw, b.canvasHeight]);
				U = Y.scroll || (Y.scroll = c.group("scroll").insertAfter(Y.layerAboveDataset));
				b.xlabels = [];
				b.xlabels.show = R.show;
				b.xlabels.height = R.labelHeight;
				b.xlabels.wrap = R.wrap;
				b.xlabels.rotate = R.rotate;
				b.xlabels.data = R.data || [];
				b.xlabels.parsed = [];
				b.xlabels.css = R.css;
				b.xlabels.group = c.group("zoomline-plot-xlabels", Y.datalabels);
				Y.datalabels.transform(["T", Z, e + f + w.scrollHeight + w.labelPadding]);
				b._lcmd = R.rotate ? "y" : "x";
				if (B) B = ka.crispBound(0, e - O, 0, f, t), W = u["clip-pinrect"] = [B.x, ka.svg ? e : B.y, B.width, B.height], S = Y.zoompin = c.group("zoompin").insertBefore(i).transform(b._pingrouptransform = ["T", Z, O]).hide(), u.pinrect = c.rect(0, e - O, b._visw, f, Y.zoompin).attr({
					"stroke-width": 0,
					stroke: "none",
					fill: w.pinPaneFill,
					"shape-rendering": "crisp",
					ishot: !0
				}), u.pintracker = c.rect(Y.tracker).attr({
					transform: S.transform(),
					x: 0,
					y: e - O,
					width: 0,
					height: f,
					stroke: "none",
					fill: wa,
					ishot: !0,
					cursor: ka.svg && "ew-resize" || "e-resize"
				}).drag(function(b) {
					var c = Z + b + this.__pindragdelta,
						e = this.__pinboundleft,
						h = this.__pinboundright;
					c < e ? c = e : c > h && (c = h);
					S.transform(["T", c, O]);
					u.pintracker.transform(S.transform());
					this.__pindragoffset = b
				}, function() {
					this.__pinboundleft = 0 - W[0] + Z + (ka.svg && k || 0);
					this.__pinboundright = this.__pinboundleft + ba - W[2];
					S._.clipispath = !0
				}, function() {
					S._.clipispath = !1;
					this.__pindragdelta = this.__pindragoffset;
					delete this.__pindragoffset;
					delete this.__pinboundleft;
					delete this.__pinboundright
				}), b.pinButton = M.add("pinModeIcon", function() {
					b.activatePin(!b._zoominfo.pinned)
				}, {
					tooltip: E && w.btnSwitchToPinModeTooltext || ""
				});
				t++;
				B = ka.crispBound(k - t, e + f + t, h + t + t, w.scrollHeight, t);
				t--;
				u.zoomscroller = c.scroller(B.x + (A && -1 || t % 2), B.y - (A && 4 || 2), B.width - (!A && 2 || 0), B.height, !0, {
					showButtons: !0,
					scrollRatio: V.vdl / (V.clen - !! V.clen),
					scrollPosition: [V.dsi / (V.clen - V.vdl - 1), !1]
				}, U).attr({
					fill: w.scrollColor,
					r: A && 2 || 0
				}).scroll(b.updatePlotZoomline, b);
				A && u.zoomscroller.shadow(!0);
				va(b, function(c) {
					var e = c.selectionLeft - k,
						c = e + c.selectionWidth;
					b.crossline && b.crossline.hide();
					b[b._zoominfo.pinned ? "pinRangePixels" : "zoomRangePixels"](e, c)
				}, {
					attr: {
						stroke: w.zoomPaneStroke,
						fill: w.zoomPaneFill,
						strokeWidth: 0
					}
				});
				b.zoomOutButton = M.add("zoomOutIcon", function() {
					b.zoomOut()
				}, {
					tooltip: E && w.btnZoomOutTooltext || ""
				})[T && "show" || "hide"]();
				b.resetButton = M.add("resetIcon", function() {
					b.resetZoom()
				}, {
					tooltip: E && w.btnResetChartTooltext || ""
				}).hide();
				B = b.resetButton.attr("fill");
				B[2] = "rgba(255,255,255,0)";
				b.resetButton.attr("fill", [B[0], B[1], B[2], B[3]]);
				b.crossline = new la(b);
				b.updatePlotZoomline()
			}
		},
		drawPlotZoomline: function(b, c) {
			var k = this.paper,
				e = this._yzero || (this._yzero = this.yAxis[0].getAxisPosition(0)),
				h = c.attrs,
				f = c.visible,
				i = f ? "show" : "hide",
				w = this.layers.dataset,
				t = b.group || (b.group = k.group("plot-zoomline-dataset", w)),
				w = b.anchorGroup || (b.anchorGroup = k.group("plot-zoomline-anchors", w)),
				k = b.graphic || (b.graphic = k.path(void 0, t)),
				e = ["T", this._visx, e];
			t.transform(e)[i]();
			w.transform(e)[i]();
			b.graphic = k.attr(h.graphics).shadow(h.shadow);
			b.attrPin = h.pin;
			b.visible = f;
			b.anchors = [];
			b.anchors.show = c.showAnchors;
			b.anchors.attrs = h.anchors;
			b.anchors.attrsShadow = h.anchorShadow;
			b.anchors.left = -(h.anchors.r + h.anchors["stroke-width"] * 0.5);
			b.anchors.right = this._visw - b.anchors.right
		},
		updatePlotZoomline: function(b, c) {
			var k = this.paper,
				e = this._ypvr,
				h = this._visw,
				f = this.xlabels,
				i = f.css,
				w = f.group,
				t = this.plots,
				A, E, B, M, u, O, U;
			!c && (c = this._zoominfo);
			B = c.oppp;
			M = c.vdl = c.dei - c.dsi;
			u = c.ppl = c.nvl ? h / c.nvl : c.oppl;
			h = c.step = (E = c.ppp = h / M) < B ? xa(B / E) : 1;
			u = c.lskip = xa(u / E / h);
			b !== void 0 ? (B = (c.clen - M - 1) * b, c.offset = (B - (B = qa(B))) * E, O = B + M) : (B = c.dsi, O = c.dei, c.offset = 0);
			M = c.norm = B % h;
			c.ddsi = B -= M;
			c.ddei = O = O + 2 * h - M;
			c.pvr = e;
			e = f.show ? xa((O - B) / h / u) : 0;
			M = c.llen - 1;
			c.llen = e;
			U = c.ppc = E * u * h;
			if (e > M) {
				u = M;
				for (M = e; u < M; u++)(A = f[u]) && A.show() || (f[u] = k.text(0, 0, "", w).css(i))
			} else {
				u = e;
				for (M += 1; u < M; u++) f[u].hide()
			}
			e = E * h < c.amrd ? 0 : xa((O - B) / h);
			i = e - c.alen;
			c.alen = e;
			if (f.wrap) f.rotate ? (f._width = f.height, f._height = U) : (f._width = U, f._height = f.height);
			for (h = t.length; h--;) {
				w = t[h];
				A = w.anchors;
				if (A.show && i) {
					E = A.attrs;
					u = 0;
					for (M = e; u < M; u++) A[u] = A[u] && A[u].show() || k.circle(0, 0, 0, w.anchorGroup).attr(E);
					u = e;
					for (M = A.length; u < M; u++) A[u] && A[u].hide()
				}
				this.drawPlotZoomlineGraphics(c, w.data, w.graphic, A, !h && f)
			}
			if (window.FC_DEV_ENVIRONMENT) FusionCharts.debugMode.enabled() ? (this.debug = this.debug || ($("#fc-zoominfo").length || $("body").append('<pre id="fc-zoominfo">'), $("#fc-zoominfo").css({
				position: "absolute",
				left: "10px",
				top: "0",
				"pointer-events": "none",
				opacity: 0.7,
				width: "250px",
				zIndex: "999",
				border: "1px solid #cccccc",
				"box-shadow": "1px 1px 3px #cccccc",
				background: "#ffffff"
			})), this.debug.text(JSON.stringify(c, 0, 2))) : ($("#fc-zoominfo").remove(), delete this.debug)
		},
		drawPlotZoomlineGraphics: function(b, c, k, e, h) {
			var f = this.smartLabel,
				i = [],
				w = !b.cnd,
				t = b.ddei,
				A = b.clen,
				B = b.step,
				E = b.lskip,
				M = b.ppp,
				u = b.offset,
				O = b.pvr,
				U = this._visw,
				R = this._visout,
				T = this._lcmd,
				W = "M",
				S, Z, ba = h && h[0],
				ja, V, e = e[0],
				ga = {},
				ka = {},
				la, ca = 0,
				ea, na, ma = -b.norm,
				b = b.ddsi,
				qa = 0;
			if (ba) h.group.transform(["T", -u, 0]), na = h.wrap, ja = h._height, V = h._width;
			for (; b <= t; b += B, ma += B) if (ea = ca / 3 + qa, la = ma * M, (S = c[b]) === void 0 ? (w && (W = "M"), Z = R, h = la - u, S = R, qa++) : (i[ca++] = W, i[ca++] = Z = h = la - u, i[ca++] = S *= O, W = "L"), e && (e = e.attr((ga.cx = Z, ga.cy = S, ga)).next), ba && !(ea % E)) ea = ba.attrs, Z = this.getParsedLabel(b), h = h < 0 || h > U ? R : la, ba._prevtext === Z ? delete ka.text : ka.text = ba._prevtext = Z, ea[T] === h ? delete ka[T] : ka[T] = h, na && Z && (ka.text = f.getSmartText(Z, V, ja).text), ba = ba.attr(ka).next;
			if (t >= A) {
				if ((S = c[A - 1]) !== void 0) ma -= t - A, i[ca++] = "L", i[ca++] = ma * M - u, i[ca++] = S * O;
				e && e.attr((ga.cx = R, ga.cy = R, ga))
			}
			k.attr("path", i)
		},
		legendClick: function(b) {
			var c = !b.visible,
				k = c ? "show" : "hide";
			b.group[k]();
			b.anchorGroup[k]();
			this.base.legendClick.apply(this, arguments);
			return b.visible = c
		}
	}, Fa["renderer.cartesian"]);
	la = function(b) {
		var c = b.paper,
			k = this.left = b._visx,
			e = this.width = b._visw,
			h = b.canvasTop,
			f = b.canvasHeight,
			i = this._visout = b._visout,
			w = this.plots = b.plots,
			t = b.layers.dataset,
			A = this.group = c.group("crossline-labels", t).attr({
				transform: ["T", k, b._yzero]
			});
		this.tracker = c.rect(k, h, e, f, t).attr({
			stroke: "none",
			"stroke-width": 0,
			fill: wa
		}).toFront().mousedown(this.onMouseDown, this).mouseup(this.onMouseUp, this, !0).mouseout(this.onMouseOut, this).mousemove(this.onMouseMove, this);
		this.line = c.path(void 0, t).attr({
			path: ["M", k, h, "l", 0, f],
			"stroke-opacity": 0.2
		}).toBack();
		k = this.labels = c.set();
		e = b.options.crossline.style;
		this.hide();
		this.pixelRatio = b._ypvr;
		this.positionLabels = b.xlabels || {
			data: [],
			parsed: []
		};
		this.getZoomInfo = function() {
			return b._zoominfo
		};
		this.getDataIndexFromPixel = function(c) {
			return b.getValuePixel(c)
		};
		this.getPositionLabel = function(c) {
			return b.getParsedLabel(c)
		};
		h = 0;
		for (f = w.length; h < f; h++) t = w[h], t = t.graphic.attrs.stroke, k.push(c.text(0, i, "", A).css(e).attr({
			fill: t,
			"text-bound": ["rgba(255,255,255,0.8)", "rgba(0,0,0,0.2)", 1, 2]
		}))
	};
	la.prototype.onMouseOut = function() {
		this.hide()
	};
	la.prototype.onMouseDown = function() {
		this.hide();
		this._mouseIsDown = !0
	};
	la.prototype.onMouseUp = function() {
		this.hide();
		delete this._mouseIsDown
	};
	la.prototype.onMouseMove = function(b) {
		if (!this._mouseIsDown) {
			var c = this.getZoomInfo(),
				k = this.line,
				e = c.step,
				h = c.ppp * e,
				b = (b.layerX || b.x) - this.left,
				f, b = (b += h / 2 + c.offset) - b % h;
			f = (f = this.getDataIndexFromPixel(b)) + f % e;
			b -= c.offset;
			k.transform(["T", za(b), 0]);
			if (f !== this.position || this.hidden) this.position = f, this.lineX = b, this.updateLabels();
			this.hidden && this.show()
		}
	};
	la.prototype.updateLabels = function() {
		var b = this.labels,
			c = this.plots,
			f = this.width,
			e = this.position,
			h = this.lineX,
			i = za(h),
			t = this.pixelRatio,
			w = this._visout,
			B, A;
		b.forEach(function(b, E) {
			B = c[E];
			A = B.data[e];
			b.attr({
				text: A + "",
				x: i,
				y: A === void 0 || !B.visible ? w : A * t,
				"text-anchor": h <= 0 && "start" || h >= f && "end" || "middle"
			})
		});
		this.positionLabel && this.positionLabel.attr({
			x: h + this.left,
			text: this.getPositionLabel(e)
		})
	};
	la.prototype.show = function() {
		this.hidden = !1;
		this.group.attr("visibility", "visible");
		this.line.attr("visibility", "visible");
		this.positionLabel && this.positionLabel.attr("visibility", "visible")
	};
	la.prototype.hide = function() {
		this.hidden = !0;
		this.group.hide();
		this.line.hide()
	};
	ka.addSymbol({
		pinModeIcon: function(b, c, f) {
			var e = f * 0.5,
				h = b - f,
				i = b + f,
				t = b - e,
				w = b + e,
				B = b + 0.5,
				A = B + 1,
				E = B + 1.5,
				O = c - f,
				M = c + e,
				u = c - e,
				e = c + (f - e);
			return ["M", h, O, "L", t, u, t, e, h, M, b - 0.5, M, b, c + f + 0.5, B, M, i, M, w, e, w, u, i, O, E, O, E, u, E, e, A, e, A, u, E, u, E, O, "Z"]
		},
		zoomOutIcon: function(b, c, f) {
			b -= f * 0.2;
			c -= f * 0.2;
			var e = f * 0.8,
				h = ka.rad(43),
				i = ka.rad(48),
				t = b + e * Ga(h),
				h = c + e * Z(h),
				w = b + e * Ga(i),
				i = c + e * Z(i),
				B = ka.rad(45),
				A = t + f * Ga(B),
				E = h + f * Z(B),
				O = w + f * Ga(B),
				f = i + f * Z(B);
			return ["M", t, h, "A", e, e, 0, 1, 0, w, i, "Z", "M", t + 1, h + 1, "L", A, E, O, f, w + 1, i + 1, "Z", "M", b - 2, c, "L", b + 2, c, "Z"]
		},
		resetIcon: function(b, c, f) {
			var e = b - f,
				h = (O.PI / 2 + O.PI) / 2;
			b += f * Ga(h);
			var h = c + f * Z(h),
				i = f * 2 / 3;
			return ["M", e, c, "A", f, f, 0, 1, 1, b, h, "L", b + i, h - 1, b + 2, h + i - 0.5, b, h]
		}
	})
}]);