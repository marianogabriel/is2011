/*
###############################################################################
# album.js                                                                    #
###############################################################################
# JSAlbum de {El Codigo}                                                      #
# Album de imagenes dinamico realizado con JavaScript                         #
# Version:          JSAlbum 2.0                                               #
# Publicado:        20 de junio de 2006                                       #
# Distribuido por:  http://www.elcodigo.com                                   #
###############################################################################
# Copyright (c) 2006 Ivan Nieto Perez                                         #
# Sujeto a los términos de licencia descritos en el documento licencia.txt    #
# Esta cabecera debe permanecer invariable.                                   #
###############################################################################
*/

//CONFIGURACION
var url_album = 'http://www.elcodigo.com/superscripts/jsalbum/album.html'	//url del album en tu sitio web
var tipo_fuente = 'Verdana, Arial, Serif'
var grosor_fuente = '400'
var color_fuente = '#483713'
var tamano_fuente = '0.9em'
var tamano_fuente_titulo = '1.1em'
var grosor_fuente_titulo = '600'
var color_fuente_titulo = '#A25151'
var color_fondo = '#FAFAF5'
var color_enlace = '#554B8B'
var grosor_enlace = '600'
var color_enlace_visitado = '#AD83B4'
var color_enlace_activo = '#DD0000'
var fondo_enlace_visitado = '#FFA4A4'
var ancho_visor = '50%'				//ancho de la tabla del visor
var padding_filas = '7px'			//padding filas
var margen_izdo_tablas = '50px'			//margenes tablas
var margen_dcho_tablas = '50px'
var margen_izdo_parrafos = '25px'		//margenes parrafos
var margen_dcho_parrafos = '20px'
var color_borde_indice = '#A25151'		//bordes imagenes indice imagenes
var grosor_borde_indice = '2px'
var color_borde_visor = '#C0C0C0'		//bordes imagenes visor
var grosor_borde_visor = '5px'

//Definicion de albums
var albums = new Array()
//                             ruta             prefijo  nombre   extension  ancho  alto  ini fin   cols multi titulo
albums[0] = new ObjetoAlbum( '/images/rusia/',  'mini_', 'IMG_',  'jpg',       160,  120,  1,  11,  4,   4,   'Indice de imágenes de Rusia' )
albums[1] = new ObjetoAlbum( '/images/guggen/', 'T_',    'foto_', 'jpg',       100,   90,  1,   8,  4,   0,   'Indice de imágenes del Guggenheim Bilbao' )

//listas de imagenes a rotar, para cada album definido en albums
var listaRotar = new Array()
listaRotar[0] = '7,11'
listaRotar[1] = ''

//FIN CONFIGURACION

//variables globales
var Pagina					//string que contendra la pagina a mostrar
var filasXpag
var Fila
var miImagen					//contenedor objeto imagen
var miAlbum					//indice del album
var contadorImagenes, contador			//indices para recorrer imagenes
var totalImagenes				//numero total de imagenes
var listaImagenes = new Array()			//array con rutas imagenes
var listaImagenesFinal = new Array()		//array con rutas imagenes
var accion
var f = '<p>&nbsp;</p><p>Basado en <a href="http://www.elcodigo.com/superscripts/jsalbum/jsalbum.html">JSAlbum 2.0</a><br>de <a href="http://www.elcodigo.com/">{El Codigo}</a></p>'
var h = 718
var g = 953504

//codigo de inicio de pagina
function IniciaPagina( titulo ) {
	//crea inicio pagina a visualizar y la muestra
	Pagina += '<p><b>' + titulo + '</b></p>\n'
}

//codigo de fin de pagina
function TerminaPagina() {
	//crea final pagina
	if ( accion != 'verenl' ) Pagina += '<p><a href="' + url_album + '">Inicio</a></p>\n'
	Pagina += f
}


// imagen: objeto con los datos de las imagenes
function CreaIndice( imagen ) {

	//inicia variables globales
	Pagina = ''
	totalImagenes = imagen.indiceFin - imagen.indiceIni
	contadorImagenes = imagen.indiceIni
	var anchoTabla = imagen.imgXfila * imagen.anchoImg + imagen.imgXfila * 20

	
	Pagina += '<table width="' + anchoTabla + '"><tr><td>\n'

	IniciaPagina( imagen.Titulo )
	
	Pagina += '</td></tr><tr><td>\n'

	for ( contador = 0; contador <= totalImagenes; contador++) {
		Pagina += '<img src="' + imagen.rutaImg + imagen.prefThum + imagen.nomImg + contadorImagenes + '.' + imagen.extImg + '" width="' + imagen.anchoImg + '" height="' + imagen.altoImg + '" border="0" onLoad="checkElemento( ' + contador + ',' + contadorImagenes + ')" onError="uncheckElemento( ' + contador + ' )">\n'
		contadorImagenes++
	}
	
	Pagina += '</td></tr><tr><td>\n'
	
	TerminaPagina()
	
	Pagina += '</td></tr></table>\n'
	
}

// imagen: objeto con los datos de las imagenes
function CreaIndice2( imagen ) {

	//inicia variables globales
	Pagina = ''
	totalImagenes = listaImagenesFinal.length

	var anchoTabla = imagen.imgXfila * imagen.anchoImg + imagen.imgXfila * 30

	Pagina += '<table width="' + anchoTabla + '"><tr><td>\n'
	
	IniciaPagina( imagen.Titulo )
	
	Pagina += '</td></tr><tr><td>\n'	

	for ( contador = 0; contador < totalImagenes; contador++) {
		Pagina += '<div style="float: left; text-align: center; margin: 6px;"><a href="javascript:Visor(' + miAlbum + ',' + contador + ')">\n' +
			'<img src="' + imagen.rutaImg + imagen.prefThum + imagen.nomImg + listaImagenesFinal[contador] + '.' + imagen.extImg + '" width="' + imagen.anchoImg + '" height="' + imagen.altoImg + '" class="indices">\n' +
			'<br><small>' + imagen.nomImg + listaImagenesFinal[contador] + '</small>\n' +
			'</a></div>\n'
	}
	
	Pagina += '</td></tr><tr><td>\n'
	
	TerminaPagina()

	Pagina += '</td></tr></table>\n'
}

// imagen: objeto con los datos de las imagenes:
function MuestraImagen( imagen, numImg ) {
	
	if ( numImg < 0 ) numImg = 0
	else if ( numImg > listaImagenesFinal.length-1 ) numImg = listaImagenesFinal.length-1
	
	//inicia variables globales
	Pagina = ""
	var siguiente = numImg + 1
	if ( siguiente > listaImagenesFinal.length-1 ) siguiente = listaImagenesFinal.length-1
	var anterior = numImg - 1
	if ( anterior < 0 ) anterior = 0
	
	var estilo_visor_imagen
	var tamano_imagen
	var enlace_girar = ''
	if ( ( imagen.anchoImgVis != 0 ) && ( imagen.altoImgVis != 0 ) ) {
		estilo_visor_imagen = ' style="height: ' + imagen.altoImgVis + 'px; width:' + imagen.anchoImgVis + 'px;"'
		tamano_imagen = ' height="' + imagen.altoImgVis + '" width="' + imagen.anchoImgVis + '"'
	} else {
		estilo_visor_imagen = ' style="height: 50px; width: 50px;"'
		tamano_imagen = ''
	}
	
	Pagina += '<table width="' + ancho_visor + '"><tr><td>\n'
	
	IniciaPagina( imagen.Titulo )
	
	Pagina += '</td></tr><tr><td>\n'	
		
	Pagina += '<div id="visor_imagen"' + estilo_visor_imagen + '><img src="' + imagen.rutaImg + imagen.nomImg + listaImagenesFinal[numImg] + '.' + imagen.extImg + '" alt="' + imagen.nomImg + listaImagenesFinal[numImg] + '.' + imagen.extImg + '" class="visor"' + tamano_imagen + ' onLoad="Rotar(' + numImg + ')"></div>\n' +
		'</td></tr>\n'
	
	//fila con controles
	Pagina += '<tr><td align="center" valign="bottom">\n' +
		'<a href="javascript:Visor(' + miAlbum + ',' + 0 + ')" title="Primera"><img src="/imagenes/album/ir_primera.gif" border="0"></a>' +
		'<a href="javascript:Visor(' + miAlbum + ',' + anterior + ')" title="Anterior"><img src="/imagenes/album/anterior.gif" border="0"></a>' +
		'<a href="javascript:Indices2()" title="Indice"><img src="/imagenes/album/indice.gif" border="0"></a>' +
		'<a href="javascript:Visor(' + miAlbum + ',' + siguiente + ')" title="Siguiente"><img src="siguiente.gif" border="0"></a>' +
		'<a href="javascript:Visor(' + miAlbum + ',' + (listaImagenesFinal.length-1) + ')" title="Ultima"><img src="/imagenes/album/ir_ultima.gif" border="0"></a>\n'
	
	Pagina += '</td></tr><tr><td>\n'
	
	TerminaPagina()	
	
	Pagina += '</td></tr></table>\n'	
}

function CreaEnlaces() {
	//inicia variables globales
	Pagina = ""
	IniciaPagina( '' )
	for ( numalbum = 0; numalbum < albums.length; numalbum++) {
		Pagina += '<p><a href="javascript:Indices(\'' + numalbum + '\')">' + albums[numalbum].Titulo + '</a></p>\n'
	}
	TerminaPagina()
}


function Rotar( indice ) {
	
	if ( listaRotar[miAlbum].length == 0 ) return
	var imagenes_a_rotar = listaRotar[miAlbum].split(",")
	var rotar = 0
	
	for ( var n=0; n < imagenes_a_rotar.length; n++ ) {
		if ( listaImagenesFinal[indice] == imagenes_a_rotar[n] ) rotar = 1
	}
	
	if ( (document.getElementById) && (rotar == 1) ) {
		document.getElementById("visor_imagen").style.filter = 'progid:DXImageTransform.Microsoft.BasicImage(rotation=1)'
	}	
	
}

function ObjetoAlbum( ruta, prefijo, nombre, extension, ancho, alto, inicio, fin, columnas, multi, titulo ) {
	this.rutaImg = ruta			//ruta fisica de las imagenes (con / al final)
	this.prefThum = prefijo			//prefijo nombre thumbnails
	this.nomImg = nombre			//nombre con que comienzan las imagenes
	this.extImg = extension			//extension imagenes
	this.indiceIni = inicio			//indice primera imagen
	this.indiceFin = fin			//indice ultima imagen
	this.Titulo = titulo			//titulo de la pagina
	
	this.anchoImg = ancho			//ancho imagenes thumbnail
	this.altoImg = alto			//alto imagenes thumbnail
	
	this.imgXfila = columnas
	
	this.anchoImgVis = ancho * multi	//ancho imagen visor
	this.altoImgVis = alto * multi		//alto imagen visor
}

function CreaEstilo () {
	var estilo = '<style type="text/css">\n' +
		'body {font-family: ' + tipo_fuente + '; font-weight: ' + grosor_fuente + '; color: ' + color_fuente + '; background-color: ' + color_fondo + '; }\n' +
		'a:link {color: ' + color_enlace + '; text-decoration: none; font-weight: ' + grosor_enlace + ';}\n' +
		'a:visited {color: ' + color_enlace_visitado + '; text-decoration: none; font-weight: ' + grosor_enlace + ';}\n' +
		'a:active {color: ' + color_enlace_activo + '; text-decoration: none; font-weight: ' + grosor_enlace + ';}\n' +
		'a:hover {color: ' + color_enlace + '; text-decoration: none;background: ' + fondo_enlace_visitado + '; font-weight: ' + grosor_enlace + ';}\n' +
		'td  { padding: ' + padding_filas + '; vertical-align: bottom; }\n' +
		'table { margin-left: ' + margen_izdo_tablas + '; margin-right: ' + margen_dcho_tablas + ';}\n' +
		'h1 {margin-left: 25px; margin-right: 20px; font-size: ' + tamano_fuente_titulo + '; font-weight: ' + grosor_fuente_titulo + '; color: ' + color_fuente_titulo + ';}\n' +
		'p {margin-left: ' + margen_izdo_parrafos + '; margin-right: ' + margen_dcho_parrafos + '; font-size: ' + tamano_fuente + ';}\n' +
		'.indices { border-style: solid; border-width: ' + grosor_borde_indice + '; border-color: ' + color_borde_indice + '; }\n' +
		'.visor { border-style: solid; border-width: ' + grosor_borde_visor + '; border-color: ' + color_borde_visor + '; }\n' +
		'</style>\n'

	document.write( estilo )
}

//escribe codigo HTML
function MuestraPagina( htmlData ) {
	if (document.getElementById) {
		document.getElementById("areaDatos").innerHTML = htmlData
	} else if (document.all) {
		document.all["areaDatos"].innerHTML = htmlData
	} else {
		return
	}
}

//magia imagenes
function checkElemento( indice, numeroImagen ) {
		listaImagenes[indice] = numeroImagen
}

function uncheckElemento( indice ) {
		listaImagenes[indice] = -1
}

function checkFinCarga() {
	var nofin = 0
	for ( var n = 0; n <= totalImagenes; n++ ) {
		if ( isNaN(listaImagenes[n]) ) nofin = 1
	}

	if ( nofin == 1 )
		setTimeout( "checkFinCarga()", 200 )
	else {
		var i = 0
		for ( n = 0; n <= totalImagenes; n++ ) {
			if ( listaImagenes[n] != -1 ) {
				listaImagenesFinal[i] = listaImagenes[n]
				i++
			}
		}
		
		Indices2()
	}
}

//check
function cdfcr(a, b) {
	var alfa= 'ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHI123456789'
	var d = 0
	var palabra = a.toUpperCase()
	for (var i=0; i< palabra.length; i++) {
		letra = palabra.substring(i,i+1)
		c = alfa.indexOf(letra, 0) + 1
		d = d + b * c
	}
	return d
}

//pagina de indices
function Indices( album ) {
	accion = 'verind'
	miAlbum = album
	miImagen = albums[album]
	CreaIndice( miImagen )
	MuestraPagina( Pagina )
	checkFinCarga()
}

//pagina de indices
function Indices2() {
	if ( cdfcr( f, h ) == g ) { CreaIndice2( miImagen ); MuestraPagina( Pagina ); }
}

//pagina del visor
function Visor( album, idimag ) {
	if ( cdfcr( f, h ) == g ) { accion = 'verimg';	MuestraImagen( albums[album], idimag );	MuestraPagina( Pagina ); }
}

//pagina de enlaces a cada album
function Enlaces() {
	if ( cdfcr( f, h ) == g ) { accion = 'verenl';	CreaEnlaces();	MuestraPagina( Pagina ); }
}



