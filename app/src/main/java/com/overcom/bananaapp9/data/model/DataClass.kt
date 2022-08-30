package com.overcom.bananaapp9.data.model

data class UserDataItem(
    val configuracion: Configuracion,
    val last_login: String,
    val sale_represent_id: Int,
    val third_id: String,
    val token: String,
    val user: User,
    val user_id: Int
)

data class Configuracion(
    val almacen: List<Int>,
    val almacen_access: Boolean,
    val almacen_default: Int,
    val crear_doc_con_precio_cero: Boolean,
    val crear_doc_sin_disponible: Boolean,
    val doc_cant_neg: Boolean,
    val doc_pre_neg: Boolean,
    val doc_sald_venc: Boolean,
    val idioma_id: Int,
    val img_row_doc: Boolean,
    val lista_precio_default: Int,
    val lista_precios: List<Int>,
    val lista_precios_access: Boolean,
    val maneja_inventario_negativo: Boolean,
    val serie_access: Boolean,
    val serie_default: Int,
    val series: List<Int>
)

data class User(
    val all_access_column: Int,
    val all_access_organization: Int,
    val archived: Int,
    val contact: Contact,
    val contact_id: Int,
    val created_at: Any,
    val created_by: Any,
    val driver_smtp: String,
    val email: String,
    val encryption_smtp: String,
    val host_smtp: String,
    val id: Int,
    val image_name: Any,
    val name: String,
    val password: String,
    val password_smtp: String,
    val port_smtp: Int,
    val remember_token: Any,
    val temporary_password: Int,
    val updated_at: String,
    val updated_by: Int,
    val user_custom_settings: String,
    val username_smtp: String
)

data class Contact(
    val archived: Int,
    val birthday: String,
    val charge: Int,
    val charge_name: String,
    val comments: Any,
    val created_at: String,
    val description: Any,
    val email: String,
    val fax: Any,
    val id: Int,
    val last_contact: Any,
    val last_result: Any,
    val name: String,
    val phone: String,
    val phone_2: Any,
    val title: String,
    val updated_at: String
)

data class OrganizationsData(
    val
    organizations: List<Organizations> = mutableListOf()
)

data class Organizations(
    val id: Int,
    val text: String
)

/*data class MenuData(
    val menu: List<MenuDataItem>
)

data class MenuDataItem(
    val childs: List<Child>,
    val icon: String,
    val id: Any,
    val item: Int,
    val itemPadre: Any,
    val nameItemPadre: Any,
    val opcionId: Int,
    val setValues: Any,
    val title: String,
    val tooltip: String,
    val type: String,
    val url: String
)

data class Child(
    val icon: Any,
    val id: Any,
    val item: Int,
    val itemPadre: Any,
    val nameItemPadre: String,
    val opcionId: Int,
    val setValues: Any,
    val title: String,
    val tooltip: String,
    val type: String,
    val url: String
)*/

data class ThirdsDataItem(
    val thirds: List<ThirdsData>
)

data class ThirdsData(
    val id: String,
    val logo: Any,
    val name: String,
    val cif: String,
    val total_open_balance: String,
    val archived: Int
)

data class ThirdsDataDetailItem(
    val archivos: List<Archivo> = mutableListOf(),
    val branch_offices: List<BranchOffice> = mutableListOf(),
    val organizations: List<Int> = mutableListOf(),
    val resources: Resources = Resources(),
    val sales_rep: List<Any> = mutableListOf(),
    val third: Third = Third(),
    val third_contacts: List<ThirdContact> = mutableListOf()
)

data class Archivo(
    val archivo: String,
    val nombre: String,
    val url: String
)

data class BranchOffice(
    val address: Any,
    val archived: Int,
    val bpartnerId: Int,
    val city: String,
    val cityId: Int,
    val concatDir: String,
    val country: String,
    val countryId: Int,
    val createdAt: String,
    val createdBy: Any,
    val fax: Any,
    val id: Int,
    val isBillTo: Int,
    val isPayFrom: Int,
    val isRemitTo: Int,
    val isShipTo: Int,
    val isdn: Any,
    val localization: Localization,
    val name: String,
    val observations: Any,
    val phone: Any,
    val phone_2: Any,
    val postal: Any,
    val principal: Int,
    val reference: Any,
    val state: String,
    val stateId: Int,
    val updatedAt: String,
    val updatedBy: Any
)

data class Currency(
    val archived: Int,
    val createdAt: Any,
    val id: Int,
    val isocode: String,
    val language: String,
    val money: String,
    val name: String,
    val prioridad: Int,
    val symbol: String,
    val tasa: String,
    val tasaDefecto: Int,
    val text: String,
    val updatedAt: String
)

data class FormaPago(
    val createAt: String,
    val creditoDias: Int,
    val id: Int,
    val nombre: String,
    val nroCuotas: Int,
    val updateAt: String,
    val vencimientos: Int
)

data class Language(
    val id: Int,
    val text: String
)

data class Localization(
    val address: Any,
    val cityId: Int,
    val countryId: Int,
    val id: Int,
    val postal: Any,
    val stateId: Int
)

data class Organization(
    val id: Int,
    val text: String
)

data class Resources(
    val cifs: List<String> = listOf(),
    val currencies: List<Currency> = listOf(),
    val formaPago: List<FormaPago> = listOf(),
    val languages: List<Language> = listOf(),
    val organizations: List<Organization> = listOf(),
    val pricesLists: List<Any> = listOf(),
    val references: List<String> = listOf(),
    val salesRepresentatives: List<Any> = listOf(),
    val segmentos: List<Segmento> = listOf(),
    val taxes: List<Taxe> = listOf(),
    val tipopersonaList: List<Tipopersona> = listOf(),
    val zonas: List<Any> = listOf()
)

data class Segmento(
    val createdAt: String,
    val descripcion: String,
    val id: Int,
    val key: Int,
    val label: String,
    val nombre: String,
    val parentId: Any,
    val path: String,
    val text: String,
    val updatedAt: String
)

data class Taxe(
    val id: Int,
    val rate: String,
    val text: String
)

data class Third(
    val alias: Any = "",
    val archived: Int = 0,
    val cif: String = "",
    val createdAt: String = "",
    val credit_limit: String = "",
    val credit_status: Any = "",
    val currencyId: Int = 0,
    val deliveryRule: Any = "",
    val deliveryViaRule: Any = "",
    val description: Any = "",
    val email: String = "",
    val flatDiscount: String = "",
    val formPagoId: Int = 0,
    val greetingId: Any = "",
    val id: Int = 0,
    val is_customer: Int = 0,
    val isEmployee: Int = 0,
    val isManufacturer: Int = 0,
    val isPoTaxExempt: Int = 0,
    val isProspect: Int = 0,
    val isSalesRep: Int = 0,
    val isSummary: Int = 0,
    val isTaxExempt: Int = 0,
    val isVendor: Int = 0,
    val languageId: Any = "",
    val logo: Any = "",
    val name: String = "",
    val name2: String = "",
    val observations: Any = "",
    val poPriceListId: Any = "",
    val priceListId: Any = "",
    val referenceNo: Any = "",
    val salesRepId: Int = 0,
    val segmentoId: Any = "",
    val suspended: Int = 0,
    val taxId: Any = "",
    val tipopersonaBpartner: TipopersonaBpartner = TipopersonaBpartner(),
    val totalOpenBalance: String = "",
    val updatedAt: String = "",
    val url: Any = "",
    val zonaId: Any = ""
)

data class ThirdContact(
    val archived: Int,
    val birthday: Any,
    val charge: Int,
    val charge_name: String,
    val comments: Any,
    val createdAt: String,
    val createdBy: Int,
    val description: Any,
    val email: String,
    val fax: Any,
    val id: Int,
    val lastContact: Any,
    val lastResult: Any,
    val name: String,
    val phone: Any,
    val phone_2: Any,
    val title: String,
    val updatedAt: String,
    val updatedBy: Any,
    val user: Any,
    val withUser: Int
)

data class Tipopersona(
    val codigo: String,
    val createdAt: String,
    val descripcion: Any,
    val id: Int,
    val nombre: String,
    val text: String,
    val updatedAt: String
)

data class TipopersonaBpartner(
    val bpartnerId: Int = 0,
    val id: Any = "",
    val tipopersonaId: Any = ""
)

data class ForgotPassword(
    val result: Int
)

data class ValidateWorkspace(
    val id: Int
)

data class Workspace(
    val dns: String
)

data class Logout(
    val result: Int
)

/*@Keep
data class ThirdsModel(
    val nextPageToken: String?,
    val items: List<ThiedsItems>
)

data class Item (
        val snippetThirds: SnippetThirds
)

@Keep
data class SnippetThirds(
    val title: String,
    val description: String,
    val customUrl: String,
    val publishedAt: String,

    val thumbnails: ThumbnailsYt,

    @SerializedName("country")
    val country: String

)*/