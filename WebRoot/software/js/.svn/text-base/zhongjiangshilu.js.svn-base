// JavaScript Document
var imP = function () { 
	/* ---- private vars ---- */ 
	var O = [], 
	    scr, 
	    box, 
	    img, 
	    txt, 
	    tit, 
	    W = 0, 
	    H = 0, 
	    nI = 0, 
	    sel = 0, 
	    si  = 0, 
	    ZOOM = 0, 
	    speed = .06, // animation speed 
	    delay = 1; // 1 = no delay 
 
	var dText = function() { 
		txt.style.textAlign = tit.style.textAlign = (sel<nI/2) ? "left" : "right"; 
		txt.innerHTML = O[sel].tx; 
		tit.innerHTML = O[sel].ti; 
	} 
 
	var CObj = function (n, s, x, tx, ti) { 
		this.n     = n; 
		this.dim   = s; 
		this.tx    = tx; 
		this.ti    = ti; 
		this.is    = img[n]; 
		this.iss   = this.is.style; 
		this.vz    = 0; 
		this.sx    = 0; 
		this.x0    = x; 
		this.x1    = 0; 
		this.zo    = 0; 
		this.rImg  = 1; 
		this.loaded = false; 
	} 
 
	CObj.prototype.over = function() { 
		if(this.n != sel){ 
			O[sel].dim = 100 / O[sel].rImg; 
			O[this.n].dim = ZOOM * 100; 
			sel = this.n; 
			cLeft(); 
			txt.innerHTML = tit.innerHTML = ""; 
			setTimeout(dText, 32); 
		} 
	} 
 
	CObj.prototype.anim = function () { 
		if (this.loaded) { 
			this.vz  = speed * (this.vz + (this.x1 - this.sx) * delay); 
			this.x1 -= this.vz; 
			this.sx  = (this.n === 0) ? 0 : O[this.n - 1].x0 + O[this.n - 1].dim; 
			this.zo -= (this.zo - (this.dim * this.rImg)) * speed; 
			var l    = (this.x1 * si) + 6 * (this.n + 1); 
			var w    = this.zo * si; 
			this.iss.left   = Math.round(l) + 'px'; 
			this.iss.top    = Math.round((H - w) * .5) + 'px'; 
			this.iss.width  = Math.round(w / this.rImg) + 'px'; 
			this.iss.height = Math.round(w) + 'px'; 
			if(sel === this.n){ 
				if(sel < nI * .5) { 
					tit.style.left = txt.style.left = Math.round(l + w / this.rImg + 6) + 'px'; 
				} else { 
					tit.style.left = txt.style.left = Math.round(l - (nx * .25) - 6) + 'px'; 
				} 
				txt.style.top = Math.round(-(w * this.rImg) * .15) + 'px'; 
				tit.style.top = Math.round((w * this.rImg) * .2) + 'px'; 
			} 
		} else { 
			if (this.is.complete) { 
				if (this.is.width) { 
					this.loaded = true; 
					this.rImg = this.is.height / this.is.width; 
					this.dim = (sel === this.n) ? ZOOM * 100 : 100 / this.rImg; 
					resize(); 
					this.iss.width = "0px"; 
					this.iss.visibility = "visible"; 
					if (this.n == 0 ) { 
						txt.style.visibility = "visible"; 
						tit.style.visibility = "visible"; 
					} 
				} 
			} 
		} 
	} 
 
	var cLeft = function() { 
		var l = 0; 
		for(var k = 0; k < nI; k++){ 
			var o = O[k]; 
			o.x0 = l; 
			l += o.dim; 
		} 
		return l; 
	} 
 
	var resize = function (){ 
		nx = scr.offsetWidth; 
		ny = scr.offsetHeight; 
		W  =  nx * 90 / 100; 
		var l = cLeft(); 
		si = (W - ((nI + 1) * 6)) / l; 
		H  = 100 * si + 120; 
		tit.style.width = Math.round(nx * .10) + 'px'; 
		txt.style.width = Math.round(nx * .25) + 'px'; 
		tit.style.fontSize = (nx / 20) + 'px'; 
		txt.style.fontSize = (nx / 20) + 'px'; 
		box.style.width  = Math.round(W) + 'px'; 
		box.style.height = Math.round(H) + 'px'; 
		box.style.left   = Math.round(nx / 2 - W / 2) + 'px'; 
		box.style.top    = Math.round(ny / 2 - H / 2) + 'px'; 
	} 
 
	//////////////////////////////////////////////////////////////////////////// 
	var init = function () { 
		scr = document.getElementById("screen"); 
		scr.onselectstart = new Function("return false"); 
		box = document.getElementById("box"); 
		tit = document.getElementById("tit"); 
		txt = document.getElementById("txt"); 
		img = box.getElementsByTagName("img"); 
		nI  = img.length; 
		ZOOM = nI; 
		var s = ZOOM * 100; 
		var x = 0; 
		for(var i=0; i<nI; i++) { 
			var tx = img[i].alt; 
			var ti = img[i].title; 
			var o = document.getElementById(ti) || false; 
			if (o) { 
				/* ==== hyperlink ==== */ 
				img[i].style.cursor = 'pointer'; 
				tx += '<br><a target="'+o.target+'" href="'+o.href+'">'+o.innerHTML+'</a>'; 
				img[i].onclick = new Function('window.open("'+o.href+'","'+o.target+'");'); 
			} 
			O[i] = new CObj(i, s, x, tx, ti); 
			img[i].alt = ""; 
			img[i].title = ""; 
			img[i].onmousedown = function() { return false; } 
			img[i].parent = O[i]; 
			img[i].onmouseover = function() { this.parent.over(); } 
			x += s; 
			s = 100; 
		} 
		tit.innerHTML = O[0].ti; 
		txt.innerHTML = O[0].tx; 
		resize(); 
		onresize = resize; 
		box.style.visibility = "visible"; 
		setInterval(function() { 
			for(var j in O) O[j].anim(); 
		}, 16); 
	} 
	//////////////////////////////////////////////////////////////////////////// 
	return { 
		init : init 
	} 
}(); 
 
