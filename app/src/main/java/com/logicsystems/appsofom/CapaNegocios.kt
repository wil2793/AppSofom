package com.logicsystems.appsofom

import android.app.Application
import com.logicsystems.appsofom.Adapters.IBasicListElement
import org.xml.sax.helpers.XMLReaderAdapter
import org.xmlpull.v1.XmlSerializer
import java.io.StringWriter
import java.util.*


open class ClsCapaNegocios {
    open var StrProblema = ""
    open var StrXMLReturn = ""
    //region getXML
    fun getXMLSearch(StrFolio: String, StrCliente: String, IntTypeSearch: Int): Boolean {
        var BlnReturn = false
        try {
            val OSearch = AppSofomSearch()
            OSearch.Folio = StrFolio
            OSearch.Cliente = StrCliente
            OSearch.TypeSearch = IntTypeSearch
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSetUbicacionesGPS(GeoLocalizacion: String, DteUbicacion: Date): Boolean {
        var BlnReturn = false
        try {
            val OSearch = UbicacionesGPS()
            val oUbicacion = UbicacionGPS()
            oUbicacion.GeoLocalizacion = GeoLocalizacion
            oUbicacion.DteUbicacion = DteUbicacion
            OSearch.Ubicacion.add(oUbicacion)
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSetUbicacionesGPS(Ubicaciones: List<UbicacionGPS>): Boolean{
        var BlnReturn = false
        try {
            val OSearch = UbicacionesGPS()
            for (oEach: UbicacionGPS in Ubicaciones){
                OSearch.Ubicacion.add(oEach)
            }
            this.StrXMLReturn = SerializeXML(OSearch)
        }
        catch (ex: Exception)
        {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "")
        {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLClientes(Nombres: String, Apellidos: String, RFC: String): Boolean {
        var BlnReturn = false
        try {
            val OSearch = BuscarCliente()
            val CLApp = ClienteApp()
            OSearch.Nombres = Nombres
            OSearch.Apellidos = Apellidos
            OSearch.RFC = RFC
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLGetClienteComplete(IdCliente: Int): Boolean {
        var BlnReturn = false
        try {
            val OSearch = AppSofomInfoCliente()
            OSearch.IdCliente = IdCliente
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLGetGrupoSolidarioComplete(IdGrupoSolidario: Int): Boolean {
        var BlnReturn = false
        try {
            val OSearch = AppSofomInfoGrupoSolidario()
            OSearch.IdGrupoSolidario = IdGrupoSolidario
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLGetGrupoSolidarioSearch(nombre: String): Boolean {
        var BlnReturn = false
        try {
            val OSearch = AppSofomInfoGrupoSolidario()
            OSearch.cNombreGrupo = nombre
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLGrupoSolidarioComplete(grupo: AppGrupoSolidario): Boolean {
        var BlnReturn = false
        try {
            this.StrXMLReturn = SerializeXML(grupo)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLTelefono(IdCliente: Int, nTipoCliente: Int): Boolean {
        var BlnReturn = false
        try {
            val OSearch = AppSofomInfoTelefono()
            OSearch.IdTel = 0
            OSearch.IdCliente = IdCliente
            OSearch.nTipoCliente = nTipoCliente
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSaveTelefono(IdCliente: Int, nTipoCliente: Int, IdTipoTel: Int, cTel: String): Boolean {
        var BlnReturn = false
        try {
            val OSearch = AppClienteTelefono()
            OSearch.IdTel = 0 //NUEVOS!
            OSearch.nTipoCliente = nTipoCliente
            OSearch.IdCliente = IdCliente
            OSearch.IdTipoTel = IdTipoTel
            OSearch.cTel = cTel
            OSearch.Exitoso = true
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLDeleteTelefono(IdTelefono: Int, nTipoCliente: Int, IdCliente: Int): Boolean {
        var BlnReturn = false
        try {
            val OSearch = AppSofomInfoTelefono()
            //ClienteApp CLApp = new ClienteApp();
            OSearch.IdTel = IdTelefono
            OSearch.nTipoCliente = nTipoCliente
            OSearch.IdCliente = IdCliente
            OSearch.Exitoso = true
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSaveClienteSimple(oCliente: AppClienteSimple): Boolean {
        var BlnReturn = false
        try {
            this.StrXMLReturn = SerializeXML(oCliente)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSaveClienteComplete(oCliente: AppClienteComplete): Boolean {
        var BlnReturn = false
        try {
            this.StrXMLReturn = SerializeXML(oCliente)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLEntregarPrestamo(IdPrestamo: Int, nMonto: Double): Boolean {
        return getXMLEntregarPrestamo(IdPrestamo, nMonto, Date(Long.MIN_VALUE), 0)
    }

    open fun getXMLEntregarPrestamo(IdPrestamo: Int, nMonto: Double, DteEntrega: Date, IdDispAPP: Int): Boolean {
        var BlnReturn = false
        try {
            val OSearch = GetEntregaPrestamo()
            OSearch.IdPrestamo = IdPrestamo
            OSearch.MontoEntrega = nMonto
            OSearch.FechaEntrega = DteEntrega
            OSearch.IdDispAPP = IdDispAPP
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLInfoCredito(IntIdPrestamo: Int): Boolean {
        var BlnReturn = false
        try {
            val OInfo = AppSofomInfoCred()
            OInfo.IdPrestamo = IntIdPrestamo
            this.StrXMLReturn = SerializeXML(OInfo)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSolRenovar(IntIdPrestamo: Int, IntPlazo: Int, DblMonto: Double, IdTipoPagoPrestamo: Int, DteVencimiento: Date): Boolean {
        var BlnReturn = false
        try {
            val OInfo = AppSofomSolRenovar()
            OInfo.IdPrestamo = IntIdPrestamo
            OInfo.Plazo = IntPlazo
            OInfo.Monto = DblMonto
            OInfo.IdTipoPagoPrestamo = IdTipoPagoPrestamo
            OInfo.DteVencimiento = DteVencimiento
            this.StrXMLReturn = SerializeXML(OInfo)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSolReestructurar(IntIdPrestamo: Int, DblMontoCA: Double, DteVencimiento: Date
    ): Boolean {
        var BlnReturn = false
        try {
            val OInfo = AppSofomSolReestructurar()
            OInfo.IdPrestamo = IntIdPrestamo
            OInfo.MontoAdicional = DblMontoCA
            OInfo.DteVencimiento = DteVencimiento
            this.StrXMLReturn = SerializeXML(OInfo)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLPago(oPago: AppSofomPay): Boolean {
        var BlnReturn = false
        try {
            this.StrXMLReturn = SerializeXML(oPago)
        } catch (ex: java.lang.Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLPago(IdPrestamo: Int, nPago: Double, IdMedioPago: Int, cNumCheque: String, nTipoEmisor: Int, cEmisor: String, nTipoAdelanto: Int): Boolean {
        return getXMLPago(IdPrestamo, nPago, IdMedioPago, cNumCheque, nTipoEmisor, cEmisor, nTipoAdelanto, Date(Long.MIN_VALUE), 0)
    }

    open fun getXMLPago(IdPrestamo: Int, nPago: Double, IdMedioPago: Int, cNumCheque: String, nTipoEmisor: Int, cEmisor: String, nTipoAdelanto: Int, DtePago: Date, IdPagoAPP: Int): Boolean {
        val OPago = AppSofomPay()
        OPago.IdPrestamo = IdPrestamo
        OPago.Pago = nPago
        OPago.IdMedioPago = IdMedioPago
        OPago.NumeroCheque = cNumCheque
        OPago.TipoEmisor = nTipoEmisor as Boolean
        OPago.Emisor = cEmisor
        OPago.TipoAdelanto = nTipoAdelanto
        OPago.FechaPago = DtePago
        OPago.IdPagoAPP = IdPagoAPP
        return getXMLPago(OPago)
    }

    open fun getXMLTicketPago(IdPrestamo: Int, IdPagoPrestamo: Int): Boolean {
        var BlnReturn = false
        try {
            val OTicket = SolicitudTicketPP()
            OTicket.IdPrestamo = IdPrestamo
            OTicket.IdPagoPrestamo = IdPagoPrestamo
            this.StrXMLReturn = SerializeXML(OTicket)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLReportes(IdPrestamo: Int, IdDetalle: Int): Boolean {
        var BlnReturn = false
        try {
            val OPago = PeticionReportes()
            OPago.IdReferencia = IdPrestamo
            OPago.IdDetalle = IdDetalle
            this.StrXMLReturn = SerializeXML(OPago)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLCorteCaja(_IdCorteCaja: Int, _nSaldoDeclarado: Double): Boolean {
        var BlnReturn = false
        try {
            val OCierre = CerrarCaja()
            OCierre.IdCorteCaja = _IdCorteCaja
            OCierre.SaldoDeclarado = _nSaldoDeclarado
            this.StrXMLReturn = SerializeXML(OCierre)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLPlantilla(cNombrePlantilla: String, cDescripcion: String): Boolean {
        var BlnReturn = false
        try {
            val OPlantilla = GetPlantilla()
            OPlantilla.Nombre = cNombrePlantilla
            OPlantilla.Descripcion = cDescripcion
            this.StrXMLReturn = SerializeXML(OPlantilla)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLZonas(cZona: String): Boolean {
        var BlnReturn = false
        try {
            val OZona = GetZonas()
            OZona.Nombre = cZona
            this.StrXMLReturn = SerializeXML(OZona)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSolicitud(IdPrestamo: Int, IdPlantillaCred: Int, nMontoTotal: Double, IdTipoPlazo: Int, nPlazo: Int, IdZona: Int, IdCliente: Int, IdGrupoSol: Int, DteVencimiento: Date, IdTipoPagoPrestamo: Int): Boolean {
        var BlnReturn = false
        try {
            val OSolicitud = SolicitudCredApp()
            OSolicitud.IdPrestamo = IdPrestamo
            OSolicitud.IdPlantillaCred = IdPlantillaCred
            OSolicitud.MontoTotal = nMontoTotal
            OSolicitud.IdTipoPlazo = IdTipoPlazo
            OSolicitud.Plazo = nPlazo
            OSolicitud.IdZona = IdZona
            OSolicitud.IdCliente = IdCliente
            OSolicitud.IdGrupoSolidario = IdGrupoSol
            OSolicitud.DteVencimiento = DteVencimiento
            OSolicitud.IdTipoPagoPrestamo = IdTipoPagoPrestamo
            this.StrXMLReturn = SerializeXML(OSolicitud)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLInfoNegocio(IdNegocio: Int): Boolean {
        var BlnReturn= false
        try {
            val OInfo = AppEmpresaBusqueda()
            OInfo.IdEmpresa = IdNegocio
            this.StrXMLReturn = SerializeXML(OInfo)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLNegocio(IdNegocio: Int, NombreNegocio: String, AntiguedadNeg: Int, NumEmpleados: Int, Ingreso: Double, Egreso: Double, IdGiro: Int, IdDomNegocio: Int, IdSituacionDom: Int, AntiguedadDom: Int, IdHLunes: Int, IdHMartes: Int, IdHMiercoles: Int, IdHJueves: Int, IdHViernes: Int, IdHSabado: Int, IdHDomingo: Int): Boolean{
        var BlnReturn = false
        try {
            val OSolicitud = NegocioSave()
            OSolicitud.IdNegocio = IdNegocio
            OSolicitud.NombreNegocio = NombreNegocio
            OSolicitud.AnguedadNegocio = AntiguedadNeg
            OSolicitud.NumeroEmpleados = NumEmpleados
            OSolicitud.Ingreso = Ingreso
            OSolicitud.Egreso = Egreso
            OSolicitud.IdGiroNegocio = IdGiro
            OSolicitud.IdDomicilio = IdDomNegocio
            OSolicitud.IdSituacionDom = IdSituacionDom
            OSolicitud.AntiguedadDom = AntiguedadDom
            OSolicitud.IdHorarioLU = IdHLunes
            OSolicitud.IdHorarioMA = IdHMartes
            OSolicitud.IdHorarioMI = IdHMiercoles
            OSolicitud.IdHorarioJU = IdHJueves
            OSolicitud.IdHorarioVI = IdHViernes
            OSolicitud.IdHorarioSA = IdHSabado
            OSolicitud.IdHorarioDO = IdHDomingo
            this.StrXMLReturn = SerializeXML(OSolicitud)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLNegocios(Nombre: String): Boolean {
        var BlnReturn = false
        try {
            val OSearch = GetEmpresa()
            OSearch.Nombre = Nombre
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLHorariosNeg(Nombre: String): Boolean {
        var BlnReturn = false
        try {
            val OSearch = GetHorario()
            OSearch.Horario = Nombre
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLGirosNeg(Nombre: String): Boolean{
        var BlnReturn = false
        try {
            val OSearch = GetGiro()
            OSearch.Nombre = Nombre
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSolRepCorteC(IdCorteCaja: Int): Boolean {
        var BlnReturn = false
        try {
            val OReporte = SolicitudReporteCorteC()
            OReporte.IdCorteCaja = IdCorteCaja
            this.StrXMLReturn = SerializeXML(OReporte)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLOperadores(Nombres: String, Apellidos: String): Boolean {
        var BlnReturn = false
        try {
            val OSearch = BuscarOperador()
            val OperApp = OperadorApp()
            OSearch.Nombres = Nombres
            OSearch.Apellidos = Apellidos
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLPermiso(StrPermiso: String): Boolean {
        var BlnReturn = false
        try {
            val OSearch = BuscarPermisoOperador()
            val OperApp = OperadorApp()
            OSearch.Permiso = StrPermiso
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSinPagoCapital(IdOperador: Int, MinDias: Int): Boolean {
        var BlnReturn = false
        try {
            val OSearch = GetSinPagoCapital()
            OSearch.IdOperadorCobranza = IdOperador
            OSearch.nDias = MinDias
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLCarteraOperador(IdOperador: Int, DteFechaIni: Date, DteFechaFin: Date): Boolean {
        var BlnReturn = false
        try {
            val OSearch = GetCarteraOperador()
            OSearch.IdOperador = IdOperador
            OSearch.DteFechaIni = DteFechaIni
            OSearch.DteFechaFin = DteFechaFin
            this.StrXMLReturn = SerializeXML(OSearch)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }

    open fun getXMLSaveCobroAccesorios(OCobranza: RespuestaAccesorios): Boolean {
        var BlnReturn = false
        try {
            this.StrXMLReturn = SerializeXML(OCobranza)
        } catch (ex: Exception) {
            this.StrProblema = "Error: " + ex.message
        }
        if (this.StrProblema == "") {
            BlnReturn = true
        }
        return BlnReturn
    }
    //endregion

    fun SerializeXML(OXML: Any): String {
        var StrXMLRet = ""
        val S: XmlSerializer? = null
        var Escritor: StringWriter? = null
        if (S != null){
            S.setProperty("Id", OXML)
            S.startTag("start","")
            S.attribute("id","","")
            S.endTag("end","")
            StrXMLRet = S.text("").toString()
        }

        val Serializador: XMLReaderAdapter
        return StrXMLRet
    }

    fun FindMedioPago(StrMedioPago: String): Int {
        val BlnReturn = when (StrMedioPago) {
            "Efectivo" -> 0
            "Referenciado" -> 1
            "Transferencia" -> 2
            "Cheque" -> 4
            "Deposito" -> 5
            "Por Pagar" -> 6
            "Nota De Crédito" -> 7
            "Deposito En Efectivo" -> 8
            "Bonificacion" -> 9
            else -> {
                -1
            }
        }
        return BlnReturn
    }

    fun FindTipoAdelanto(StrTipoAdelanto: String): Int{
        val BlnReturn = when (StrTipoAdelanto) {
            "Reducir Plazo" -> 1
            "Reducir Pagare" -> 2
            "Siguiente Letra" -> 3
            "Reducir Intereses" -> 4
            "Abono a Garantia" -> 5
            else -> {
                -1
            }
        }
        return BlnReturn
    }
}

class AppListaEmpresa
{
    var Empresas = mutableListOf<AppEmpresa>()
}
class AppEmpresa {
    var Empresa = ""
}
open class AppRespuesta {
    var Exitoso = false
    var Error = ""
    var Metodo = ""
}

class AppRespuestaLogIn : AppRespuesta()
{
    var NickName = ""
    var InfoTicket = ""
}

class AppSofomSearch {
    var Folio = ""
    var Cliente = ""
    var TypeSearch = 0
}

class AppSearchRespuesta : AppRespuesta()
{
    var Creditos = mutableListOf<AppCredito>()
}

class AppCredito : IBasicListElement {
    var IdPrestamo = 0
    var IdCliente = 0
    var IdClienteMoral = 0
    var IdGrupoSolidario = 0
    var Folio = ""
    var Cliente = ""
    var Monto = 0.0
    var Estado = ""
    var DteSolicitud = Date(Long.MIN_VALUE)
    var RFC = ""
    var Direccion = ""
    var nEstadoRegistro = 0
    var GeoLocalizacion = ""
    var Color = ""
    override fun getId(): Int {
        return IdPrestamo;
    }

    override fun getDescription(): String {
        return Folio;
    }

    override fun getDetailDescription(): String {
        return Cliente;
    }

    override fun getDetailDescription2(): String {
        return Direccion;
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdPrestamo = Id
        this.Folio = description
        this.Cliente = detailDescription
    }
}

class AppSofomPay {
    var IdPrestamo = 0
    var Pago = 0.0
    var IdMedioPago = 0
    var NumeroCheque = ""
    var TipoEmisor = false
    var Emisor = ""
    var TipoAdelanto = 0
    var FechaPago = Date(Long.MIN_VALUE)
    var IdPagoAPP = 0
    var Integrantes = mutableListOf<AppClienteGrupo>()
}

open class AppCobranzaRespuesta : AppRespuesta()
{
    var IdPrestamo = 0
    var Folio = ""
    var Cliente = ""
    var Pendiente = 0.0
    var AlDia = 0.0
    var Liquidar = 0.0
    var IdPagoAPP = 0

    var IdCliente = 0
    var IdClienteMoral = 0
    var IdGrupoSolidario = 0

    var Integrantes = mutableListOf<AppClienteGrupo>()
}

class PeticionReportes {
    var IdReferencia = 0
    var IdDetalle = 0
}

class HistorialPagos : AppRespuesta()
{
    var Pagos = mutableListOf<GeneralPagos>()
}

open class GeneralPagos : AppRespuesta()
{
    var IdPagoPrestamo = 0
    var Operador = ""
    var FechaPago = Date(Long.MIN_VALUE)
    var PagoTotal = 0.0
}

class DetallePagos : GeneralPagos()
{
    var Capital = 0.0
    var Interes = 0.0
    var InteresIVA = 0.0
    var Mora = 0.0
    var MoraIVA = 0.0
    var Multa = 0.0
    var MultaIVA = 0.0
    var LblCuotaFija = ""
    var CuotaFija = 0.0
    var CuotaFijaIVA = 0.0
    var LblCuotaFija1 = ""
    var CuotaFija1 = 0.0
    var CuotaFija1IVA = 0.0
    var LblCuotaFija2 = ""
    var CuotaFija2 = 0.0
    var CuotaFija2IVA = 0.0
    var Gastos = 0.0
    var Adelanto = 0.0
    var MedioPago = ""
    var Caja = ""
}

class HistorialCredito : AppRespuesta()
{
    var GeneralHistorico = mutableListOf<GeneralHistorialCredito>()
}

open class GeneralHistorialCredito : AppRespuesta()
{
    var IdCF = 0
    var NumPago = 0
    var FechaVencimiento = Date(Long.MIN_VALUE)
    var TotalPagare = 0.0
    var SaldoPagare = 0.0
}
class DetalleHistorialCredito : GeneralHistorialCredito()
{
    var FechaSaldado = Date(Long.MIN_VALUE)
    var Capital = 0.0
    var SaldoCapital = 0.0
    var Interes = 0.0
    var SaldoInteres = 0.0
    var Atraso = 0
    var Multa = 0.0
    var SaldoMulta = 0.0
    var Mora = 0.0
    var MoraPagada = 0.0
    var MoraCondonada = 0.0
    var ExistCuotaFija = false
    var LblCuotaFija = ""
    var CuotaFija = 0.0
    var SaldoCuotaFija = 0.0
    var ExistCuotaFija1 = false
    var LblCuotaFija1 = ""
    var CuotaFija1 = 0.0
    var SaldoCuotaFija1 = 0.0
    var ExistCuotaFija2 = false
    var LblCuotaFija2 = ""
    var CuotaFija2 = 0.0
    var SaldoCuotaFija2 = 0.0
}

class CorteCajaRespuesta : AppRespuesta()
{
    var IdCorteCaja = 0
    var Caja = ""
    var Operador = ""
    var PermitirOper = false
    var EstadoCorte = 0
    var EdoCierre = 0
    var SaldoInicial = 0.0
    var SaldoFinal = 0.0
    var SaldoDeclarado = 0.0

    fun NameEstadoCorte(): String {
        var Name = when (EstadoCorte) {
            EdosCorte.Abierto as Int -> "Abierto"
            EdosCorte.Cerrado as Int -> "Cerrado"
            EdosCorte.Cancelado as Int -> "Cancelado"
            EdosCorte.Revisado as Int -> "Revisado"
            else -> {
                ""
            }
        }
        return Name
    }
}

class CerrarCaja{
    var IdCorteCaja = 0
    var SaldoDeclarado = 0.0
}

class SolicitudTicketPP{
    var IdPrestamo = 0
    var IdPagoPrestamo = 0
}

class RespuestaTicketPP : AppRespuesta()
{
    var FileTicket = ""
}

open class UserApp : Application()
{
    companion object{
        var StrUser = ""
        var StrPass = ""
        var StrNickName = ""
    }
}

class PrestamoApp : Application()
{
    var IntIdPrestamo = 0
    var IntIdCobranza = 0
    var IntIdDisposicion = 0
    var DblLiquidar = 0.0
    var IntIdGrupoSolidario = 0
    var Integrantes = mutableListOf<AppClienteGrupo>()
}

class CobranzaRepuesta : AppRespuesta()
{
    var Cobranzas = mutableListOf<AppCobranzaRespuesta>()
}

//region GetPrestamosDetalle
class DisposicionMasivaRespuesta : AppRespuesta()
{
    var DetalleDisposicion = mutableListOf<RespuestaPrestamoDetalle>()
}
open class RespuestaPrestamoDetalle : AppRespuesta(){
    //PRESTAMO
    var IdPrestamo = 0
    var Folio = ""
    var Cliente = ""
    var Monto = 0.0
    var Estado = ""
    var FechaSolicitud = Date(Long.MIN_VALUE)
    var RFC = ""
    var SaldoPendiente = 0.0
    var IdDispAPP = 0
    var IdCliente = 0
    var IdClienteMoral = 0
    var IdGrupoSolidario = 0
    var cDireccion = ""

    var Integrantes = mutableListOf<AppClienteGrupo>()
}
class RespuestaAccesorios : RespuestaPrestamoDetalle(){
    var lComision = false
    var nMontoComision = 0.0
    var nSaldoComision = 0.0
    var lComisionSaldado = false
    var DtePagoComision = Date(Long.MIN_VALUE)
    var nMontoComisionPagando = 0.0

    var lGarantia = false
    var nMontoGarantia = 0.0
    var nSaldoGarantia = 0.0
    var lGarantiaSaldado = false
    var DtePagoGarantia = Date(Long.MIN_VALUE)
    var nMontoGarantiaPagando = 0.0

    var lPrima = false
    var nMontoPrima = 0.0
    var nSaldoPrima = 0.0
    var lPrimaSaldado = false
    var DtePagoPrima = Date(Long.MIN_VALUE)
    var nMontoPrimaPagando = 0.0

    var lGastos = false
    var nMontoGastos = 0.0
    var nSaldoGastos = 0.0
    var lGastosSaldado = false
    var DtePagoGastos = Date(Long.MIN_VALUE)
    var nMontoGastosPagando = 0.0

    var lOtros = false
    var nMontoOtros = 0.0
    var nSaldoOtros = 0.0
    var lOtrosSaldado = false
    var DtePagoOtros = Date(Long.MIN_VALUE)
    var nMontoOtrosPagando = 0.0

    var nMontoTotalAccesorios = 0.0
    var nMontoPendienteAccesorios = 0.0
    var IdMedioPago = 0
    var IdIntegrantePagando = 0
}
//endregion

//region EntregaPrestamo
class SpDetEntregaPrestamo{
    var Id = 0
    var Descripcion = ""
    fun SpDetEntregaPrestamo(_Id: Int, _Descripcion: String) {
        this.Id = _Id
        this.Descripcion = _Descripcion
    }
}
class GetEntregaPrestamo{
    var IdPrestamo = 0
    var MontoEntrega = 0.0
    var FechaEntrega = Date(Long.MIN_VALUE)
    var IdDispAPP = 0
}
//endregion

//region SetUbicacionGPS
class UbicacionesGPS{
    var Ubicacion = mutableListOf<UbicacionGPS>()
}
class UbicacionGPS{
    var GeoLocalizacion = ""
    var DteUbicacion = Date(Long.MIN_VALUE)
}
class RespuestaSetUbicacionesGPS : AppRespuesta(){
    var Respuesta = ""
}
//endregion

open class AppSofomInfoCred{
    var IdPrestamo = 0
}

class AppSofomSolRenovar : AppSofomInfoCred(){
    var Plazo = 0
    var Monto = 0.0
    var CapitalAdicional = 0.0
    var IdTipoPagoPrestamo = 0
    var DteVencimiento = Date(Long.MIN_VALUE)
}

class AppSofomSolReestructurar : AppSofomInfoCred(){
    var MontoAdicional = 0.0
    var DteVencimiento = Date(Long.MIN_VALUE)
}

open class AppSofomRespInfoGnal : AppRespuesta(){
    var IdPrestamo = 0
    var Folio = ""
    var Cliente = ""
    var MontoPrestamo = 0.0
    var IdTipoPagoPrestamo = 0
    var IdTipoPlazo = 0
    var DteVencimiento = Date(Long.MIN_VALUE)
}

class AppSofomRespReest : AppSofomRespInfoGnal(){
    var Capital = 0.0
    var Interes = 0.0
    var IdReestructura = 0
    var FolioReestructura = ""
    var PlazoReestructura = 0
    var MontoReestructura = 0.0
    var MontoAdicionalReest = 0.0
    var IsBuenCliente = false
}

class AppSofomRespRenov : AppSofomRespInfoGnal(){
    var MontoMinPlantilla = 0.0
    var MontoMaxPlantilla = 0.0
    var IdRenovacion = 0
    var FolioRenovacion = ""
    var PlazoRenovacion = 0
    var MontoRenovacion = 0.0
}

class ClienteApp : Application(){
    var IntIdCliente = 0
}

class BuscarCliente{
    var Nombres = ""
    var Apellidos = ""
    var RFC = ""
}

class OperadorApp : Application(){
    var IntIdOperador = -1
}

class BuscarOperador{
    var Nombres = ""
    var Apellidos = ""
}

class RespuestaGetClientes : AppRespuesta(){
    var Respuesta = ""
}

class AppSofomInfoCliente{
    var IdCliente = 0
}

class AppSofomInfoGrupoSolidario : IBasicListElement {
    var IdGrupoSolidario = 0
    var cNombreGrupo = ""
    var nRenovacion = 0
    var nMonto = 0.0

    override fun getId(): Int {
        return IdGrupoSolidario;
    }

    override fun getDescription(): String {
        return cNombreGrupo;
    }

    override fun getDetailDescription(): String {
        return ""
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdGrupoSolidario = Id
        this.cNombreGrupo = description
    }
}

class AppGrupoSolidario : AppRespuesta(){
    var IdGrupoSolidario = 0
    var cNombre = ""
    var nMonto = 0.0
    var nGarantiaLiquida = 0.0
    var cObservaciones = ""
    var nRenovacion = 0
    var IdGrupoRenovacion = 0
    var cNombreRenovacion = ""

    var Integrantes = mutableListOf<AppClienteGrupo>()
    var showRolGrupal = true
    var IdPrestamo = 0
    var IdEdoPrestamo = 0
    var nPorcentajeGarantia = 0.0
}

class AppClienteGrupo : AppClienteSimple(){
    var IdRelGrupoSCliente = 0
    var nMonto = 0.0
    var nGarantiaLiquida = 0.0
    var IdRolGrupal = 1
    var nMontoPago = 0.0
    var nGarantiaPagada = 0.0
    var lGarantiaSaldada = false
    var DteSaldadoGarantia = Date(Long.MIN_VALUE)
}

class AppGrupoSolidarioBusquedaRespuesta : AppRespuesta(){
    var Grupos = mutableListOf<AppSofomInfoGrupoSolidario>()
}

class AppClienteComplete : AppClienteSimple(){
    var Genero = ""
    var DteNacimiento = Date(Long.MIN_VALUE)
    var IdEntidadFederativa = 0
    var EntidadFederativa = ""
    var IdNacionalidad = 0
    var Nacionalidad = ""
    var Curp = ""
    var IdEstadoCivil = 0
    var EstadoCivil = ""
    var IdEscolaridad = 0
    var Escolaridad = ""
    var Salario = 0.0
    var OtrosIngresos = 0.0
    var cOtrosIngresos = ""
    var GastoRenta = 0.0
    var OtrosGastos = 0.0
    var GastoNomina = 0.0

    var IdRefLab1 = 0
    var IdRefLab2 = 0
    var IdRefVec1 = 0
    var IdRefVec2 = 0

    var RefLab1 = ""
    var RefLab2 = ""
    var RefVec1 = ""
    var RefVec2 = ""

    var IdNegocio = 0
    var Negocio = ""
    var IdDomicilio = 0
    var Domicilio = ""
    var IdClienteDireccion = 0

    var CurpSolicitada = false
}

open class AppClienteSimple : AppRespuesta(), IBasicListElement {
    var IdCliente = 0
    var Nombre1 = ""
    var Nombre2 = ""
    var ApellidoPat = ""
    var ApellidoMat = ""
    var RFC = ""
    var Observaciones = ""
    fun getNombreCompleto(): String {
        return ((this.Nombre1.trim() + " " + this.Nombre2.trim()).trim() + " " + (this.ApellidoPat.trim() + " " + this.ApellidoMat.trim()).trim()).trim()
    }
    override fun getId(): Int {
        return IdCliente;
    }

    override fun getDescription(): String {
        return this.getNombreCompleto()
    }

    override fun getDetailDescription(): String {
        return RFC
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdCliente = Id
        this.Nombre1 = description
        this.RFC = detailDescription
    }
}

class ClienteRepuesta : AppRespuesta(){
    var Clientes = mutableListOf<AppClienteSimple>()
}

open class AppOperadorSimple : AppRespuesta(), IBasicListElement {
    var IdOperador = 0
    var Operador = ""
    var Nivel = ""
    override fun getId(): Int {
        return this.IdOperador
    }

    override fun getDescription(): String {
        return this.Operador
    }

    override fun getDetailDescription(): String {
        return this.Nivel;
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdOperador = Id
        this.Operador = description
        this.Nivel = detailDescription
    }
}
class BuscarPermisoOperador{
    var Permiso = ""
}

class AppOperadorPermiso : AppOperadorSimple(){
    var PermisoResult = 0
}

class AppOperadorCartera : AppOperadorSimple(){
    var SaldoCapital = 0.0
    var SaldoInteres = 0.0
    var TotalCapitalCobrado = 0.0
    var TotalInteresCobrado = 0.0
    var CapitalEsperado = 0.0
    var InteresEsperado = 0.0
    var CapitalCobradoRango = 0.0
    var InteresCobradoRango = 0.0
}

class GetSinPagoCapital : AppRespuesta(){
    var nDias = 0
    var IdOperadorCobranza = 0
    var IsMovil = true
}

class GetCarteraOperador : AppRespuesta(){
    var IdOperador = 0
    var DteFechaIni = Date(Long.MIN_VALUE)
    var DteFechaFin = Date(Long.MIN_VALUE)
}

class OperadorRepuesta : AppRespuesta(){
    var Operadores = mutableListOf<AppOperadorSimple>()
}

class OperadorCarteraRepuesta : AppRespuesta(){
    var CarteraOperador = mutableListOf<AppOperadorCartera>()
}

class AppSinPagoCapital{
    var Operador = ""
    var cFolio = ""
    var cCliente = ""
    var cTelefono = ""
    var Dias = 0
    var CapEsperado = 0.0
}

class SinPagoCapitalRespuesta : AppRespuesta(){
    var SinPagar = mutableListOf<AppSinPagoCapital>()
}

//region Datos PLD
class ClientePLD : AppRespuesta(){
    var IdCliente = 0
    var Nombre = ""
    var Apellidos = ""
    var DatosPld: DatosClientePLD? = null
    var Documentos = mutableListOf<DatosDocumentoPLD>()
}
class DatosClientePLD{
    var Funcionario = 0
    var Cargo = 0
    var Parentesco = 0
    var IdClienteParentesco = 0
    var NombreClienteParentesco = ""
    var Activo = 0
    var FechaBaja = Date(Long.MIN_VALUE)
}
class DatosDocumentoPLD{
    var Id = 0
    var IdTipoDoc = 0
    var NombreTipoDoc = ""
    var Existe = 0
}
class SolicitudPLD{
    var IdCliente = 0
}
class SaveDocumentoPLD{
    var Id = 0
    var IdCliente = 0
    var IdTipoDoc = 0
    var File = ""
    var FileName = ""
}
class SaveDocumentoPLDResponse : AppRespuesta(){
    var IdCliente = 0
    var Id = 0
    var Existe = 0
}
//endregion

//region GetPlantillas
class GetPlantilla{
    var Nombre = ""
    var Descripcion = ""
}
class AppPlantilla : IBasicListElement {
    var IdPlantilla = 0
    var NombrePlantilla = ""
    var Descripcion = ""
    var IdTipoPagoPrestamo = 0

    override fun getId(): Int {
        return this.IdPlantilla
    }

    override fun getDescription(): String {
        return this.NombrePlantilla
    }

    override fun getDetailDescription(): String {
        return this.Descripcion
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdPlantilla = Id
        this.NombrePlantilla = description
        this.Descripcion = detailDescription
    }
}
class PlantillaRespuesta : AppRespuesta(){
    var Plantilla = mutableListOf<AppPlantilla>()
}
//endregion

//region GetZonas
class GetZonas{
    var Nombre = ""
    var Descripcion = ""
}
class AppZonas : IBasicListElement {
    var IdZona = 0
    var NombreZona = ""

    override fun getId(): Int {
        return this.IdZona
    }

    override fun getDescription(): String {
        return this.NombreZona
    }

    override fun getDetailDescription(): String {
        return ""
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdZona = Id;
        this.NombreZona = description
    }

}

class ZonasRespuesta : AppRespuesta(){
    var Plantilla = mutableListOf<AppZonas>()
}
//endregion

//region Response Solicitud de Credito
class SolicitudCredApp : AppRespuesta(){
    var IdPrestamo = 0
    var IdPlantillaCred = 0
    var NombrePantilla = ""
    var Folio = ""
    var MontoTotal = 0.0
    var IdTipoPagoPrestamo = 0
    var IdTipoPlazo = 0
    var Plazo = 0
    var DteVencimiento = Date(Long.MIN_VALUE)
    var IdZona = 0
    var Zona = ""
    var IdCliente = 0
    var Cliente = ""
    var IdEdoPrestamo = 0
    var IdGrupoSolidario = 0
    var GrupoSolidario = ""
}
//endregion

//region Direcciones
class GetDireccion{
    var Asentamiento = ""
    var Calle = ""
    var Ext = ""
    var CP = ""
}
class AppDireccion : AppRespuesta(), IBasicListElement {
    var IdDireccion = 0
    var Calle = ""
    var NumExterior = ""
    var NumInterior = ""
    var Cruzamiento1 = ""
    var Cruzamiento2 = ""
    var Formato = 0
    var IdSepomex = 0
    var TipoAsentamiento = ""
    var Asentamiento = ""
    var NoEstructurado = ""
    var Municipio = ""
    var Estado = ""
    var CP = ""
    var Geolocalizacion = ""

    override fun getId(): Int {
        return IdDireccion
    }

    override fun getDescription(): String {
        var d1 = when (Formato) {
            //0: noasignado, 1: numerada
            0, 1 -> "Calle " + Calle + " #" + NumExterior + " " + (if (NumInterior !== "") "Int $NumInterior" else "") + if (Cruzamiento1 !== "") " entre " + Cruzamiento1 + " " + (if (Cruzamiento2 !== "") " y $Cruzamiento2" else "") else ""
            //Nombrada
            2 -> Calle + " " + NumExterior + " " + (if (NumInterior !== "") " $NumInterior" else "") + if (Cruzamiento1 !== "") " entre " + Cruzamiento1 + " " + (if (Cruzamiento2 !== "") " y $Cruzamiento2" else "") else ""
            //No estructurada
            3 -> Calle
            else -> {
                Calle + " " + NumExterior + " " + (if (NumInterior !== "") " $NumInterior" else "") + if (Cruzamiento1 !== "") " entre " + Cruzamiento1 + " " + (if (Cruzamiento2 !== "") " y $Cruzamiento2" else "") else ""
            }
        }
        return d1.trim()
    }

    fun getDetailSepomex(): String {
        return "$Asentamiento C.P:$CP $Municipio, $Estado"
    }

    override fun getDetailDescription(): String {
        return (if (Formato != 3) "$TipoAsentamiento: " else "") + getDetailSepomex()
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdDireccion = Id
        this.Calle = description
        this.Formato = 3
    }
}
class DireccionRespuesta : AppRespuesta(){
    var Direccion = mutableListOf<AppDireccion>()
}
//endregion

//region Asentamientos
class GetAsentamiento{
    var CP = ""
    var Asentamiento = ""
    var Municipio = ""
    var Ciudad = ""
    var Estado = ""
}
class AppAsentamiento : IBasicListElement {
    var IdSepomex = 0
    var CP = ""
    var Asentamiento = ""
    var Municipio = ""
    var Ciudad = ""
    var Estado = ""

    override fun getId(): Int {
        return IdSepomex
    }

    override fun getDescription(): String {
        return "$Asentamiento C.P:$CP $Municipio, $Estado"
    }

    override fun getDetailDescription(): String {
        return ""
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdSepomex = Id
        this.Asentamiento = description
    }
}
class AsentamientoRespuesta : AppRespuesta(){
    var Asentamiento = mutableListOf<AppAsentamiento>()
}
//endregion

//region Telefono
open class AppSofomInfoTelefono : AppRespuesta(){
    var IdTel = 0
    var nTipoCliente = 0
    var IdCliente = 0
}
class AppClienteTelefono : AppSofomInfoTelefono(){
    var IdTipoTel = 0
    var cTipoTel = ""
    var cTel = ""
}
class ClienteTelefonoRepuesta : AppRespuesta(){
    var Telefonos = mutableListOf<AppClienteTelefono>()
}
//endregion

//region GetEmpresas
class GetEmpresa{
    var Nombre = ""
}
class AppEmpresaBusqueda : IBasicListElement {
    var IdEmpresa = 0
    var Empresa = ""

    override fun getId(): Int {
        return this.IdEmpresa
    }

    override fun getDescription(): String {
        return this.Empresa
    }

    override fun getDetailDescription(): String {
        return ""
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdEmpresa = Id
        this.Empresa = description
    }
}
class EmpresaRespuesta : AppRespuesta(){
    var Empresa = mutableListOf<AppEmpresaBusqueda>()
}
class NegocioSave : AppRespuesta(){
    var IdNegocio = 0
    var NombreNegocio = ""
    var AnguedadNegocio = 0
    var NumeroEmpleados = 0
    var Ingreso = 0.0
    var Egreso = 0.0
    var IdGiroNegocio = 0
    var GiroNegocio = ""
    var IdDomicilio = 0
    var Domicilio = ""
    var IdSituacionDom = 0
    var AntiguedadDom = 0
    var IdHorarioLU = 0
    var HorarioLU = ""
    var IdHorarioMA = 0
    var HorarioMA = ""
    var IdHorarioMI = 0
    var HorarioMI = ""
    var IdHorarioJU = 0
    var HorarioJU = ""
    var IdHorarioVI = 0
    var HorarioVI = ""
    var IdHorarioSA = 0
    var HorarioSA = ""
    var IdHorarioDO = 0
    var HorarioDO = ""
}
//endregion

//region GetGiros
class GetGiro{
    var Nombre = ""
}
class AppGiroBusqueda : IBasicListElement {
    var IdGiro = 0
    var Giro = ""

    override fun getId(): Int {
        return this.IdGiro
    }

    override fun getDescription(): String {
        return this.Giro.trim()
    }

    override fun getDetailDescription(): String {
        return ""
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdGiro = Id;
        this.Giro = description;
    }
}
class GiroRespuesta : AppRespuesta(){
    var Giro = mutableListOf<AppGiroBusqueda>()
}
//endregion

//region GetHorarios
class GetHorario{
    var Horario = ""
}
class AppHorario : IBasicListElement {
    var IdHorario = 0
    var Horario = ""

    override fun getId(): Int {
        return this.IdHorario
    }

    override fun getDescription(): String {
        return this.Horario.trim()
    }

    override fun getDetailDescription(): String {
        return ""
    }

    override fun getDetailDescription2(): String {
        return ""
    }

    override fun init(
        Id: Int,
        description: String,
        detailDescription: String,
        detailDescription2: String
    ) {
        this.IdHorario = Id
        this.Horario = description
    }
}
class HorarioRespuesta : AppRespuesta(){
    var Horario = mutableListOf<AppHorario>()
}
//endregion

class SolicitudReporteCorteC{
    var IdCorteCaja = 0
}
class RespuestaReporteCorteC : AppRespuesta(){
    var FileReporte = ""
}