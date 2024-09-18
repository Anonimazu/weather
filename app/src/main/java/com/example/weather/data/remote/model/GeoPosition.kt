package com.example.weather.data.remote.model
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GeoPosition(
    @SerializedName("country")
    var country: String? = "",
    @SerializedName("lat")
    var lat: Double? = null,
    @SerializedName("local_names")
    var localNames: LocalNames? = LocalNames(),
    @SerializedName("lon")
    var lon: Double? = null,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("state")
    var state: String? = ""
) : Parcelable

@Parcelize
data class LocalNames(
    @SerializedName("ab")
    var ab: String? = "",
    @SerializedName("af")
    var af: String? = "",
    @SerializedName("am")
    var am: String? = "",
    @SerializedName("an")
    var an: String? = "",
    @SerializedName("ar")
    var ar: String? = "",
    @SerializedName("ascii")
    var ascii: String? = "",
    @SerializedName("av")
    var av: String? = "",
    @SerializedName("ay")
    var ay: String? = "",
    @SerializedName("az")
    var az: String? = "",
    @SerializedName("ba")
    var ba: String? = "",
    @SerializedName("be")
    var be: String? = "",
    @SerializedName("bg")
    var bg: String? = "",
    @SerializedName("bh")
    var bh: String? = "",
    @SerializedName("bi")
    var bi: String? = "",
    @SerializedName("bm")
    var bm: String? = "",
    @SerializedName("bn")
    var bn: String? = "",
    @SerializedName("bo")
    var bo: String? = "",
    @SerializedName("br")
    var br: String? = "",
    @SerializedName("bs")
    var bs: String? = "",
    @SerializedName("ca")
    var ca: String? = "",
    @SerializedName("ce")
    var ce: String? = "",
    @SerializedName("co")
    var co: String? = "",
    @SerializedName("cs")
    var cs: String? = "",
    @SerializedName("cu")
    var cu: String? = "",
    @SerializedName("cv")
    var cv: String? = "",
    @SerializedName("cy")
    var cy: String? = "",
    @SerializedName("da")
    var da: String? = "",
    @SerializedName("de")
    var de: String? = "",
    @SerializedName("ee")
    var ee: String? = "",
    @SerializedName("el")
    var el: String? = "",
    @SerializedName("en")
    var en: String? = "",
    @SerializedName("eo")
    var eo: String? = "",
    @SerializedName("es")
    var es: String? = "",
    @SerializedName("et")
    var et: String? = "",
    @SerializedName("eu")
    var eu: String? = "",
    @SerializedName("fa")
    var fa: String? = "",
    @SerializedName("feature_name")
    var featureName: String? = "",
    @SerializedName("ff")
    var ff: String? = "",
    @SerializedName("fi")
    var fi: String? = "",
    @SerializedName("fj")
    var fj: String? = "",
    @SerializedName("fo")
    var fo: String? = "",
    @SerializedName("fr")
    var fr: String? = "",
    @SerializedName("fy")
    var fy: String? = "",
    @SerializedName("ga")
    var ga: String? = "",
    @SerializedName("gd")
    var gd: String? = "",
    @SerializedName("gl")
    var gl: String? = "",
    @SerializedName("gn")
    var gn: String? = "",
    @SerializedName("gu")
    var gu: String? = "",
    @SerializedName("gv")
    var gv: String? = "",
    @SerializedName("ha")
    var ha: String? = "",
    @SerializedName("he")
    var he: String? = "",
    @SerializedName("hi")
    var hi: String? = "",
    @SerializedName("hr")
    var hr: String? = "",
    @SerializedName("ht")
    var ht: String? = "",
    @SerializedName("hu")
    var hu: String? = "",
    @SerializedName("hy")
    var hy: String? = "",
    @SerializedName("ia")
    var ia: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("ie")
    var ie: String? = "",
    @SerializedName("ig")
    var ig: String? = "",
    @SerializedName("io")
    var io: String? = "",
    @SerializedName("is")
    var isX: String? = "",
    @SerializedName("it")
    var `it`: String? = "",
    @SerializedName("ja")
    var ja: String? = "",
    @SerializedName("jv")
    var jv: String? = "",
    @SerializedName("ka")
    var ka: String? = "",
    @SerializedName("kk")
    var kk: String? = "",
    @SerializedName("kl")
    var kl: String? = "",
    @SerializedName("km")
    var km: String? = "",
    @SerializedName("kn")
    var kn: String? = "",
    @SerializedName("ko")
    var ko: String? = "",
    @SerializedName("ku")
    var ku: String? = "",
    @SerializedName("kv")
    var kv: String? = "",
    @SerializedName("kw")
    var kw: String? = "",
    @SerializedName("ky")
    var ky: String? = "",
    @SerializedName("lb")
    var lb: String? = "",
    @SerializedName("li")
    var li: String? = "",
    @SerializedName("ln")
    var ln: String? = "",
    @SerializedName("lo")
    var lo: String? = "",
    @SerializedName("lt")
    var lt: String? = "",
    @SerializedName("lv")
    var lv: String? = "",
    @SerializedName("mg")
    var mg: String? = "",
    @SerializedName("mi")
    var mi: String? = "",
    @SerializedName("mk")
    var mk: String? = "",
    @SerializedName("ml")
    var ml: String? = "",
    @SerializedName("mn")
    var mn: String? = "",
    @SerializedName("mr")
    var mr: String? = "",
    @SerializedName("ms")
    var ms: String? = "",
    @SerializedName("mt")
    var mt: String? = "",
    @SerializedName("my")
    var my: String? = "",
    @SerializedName("na")
    var na: String? = "",
    @SerializedName("ne")
    var ne: String? = "",
    @SerializedName("nl")
    var nl: String? = "",
    @SerializedName("nn")
    var nn: String? = "",
    @SerializedName("no")
    var no: String? = "",
    @SerializedName("nv")
    var nv: String? = "",
    @SerializedName("ny")
    var ny: String? = "",
    @SerializedName("oc")
    var oc: String? = "",
    @SerializedName("om")
    var om: String? = "",
    @SerializedName("or")
    var or: String? = "",
    @SerializedName("os")
    var os: String? = "",
    @SerializedName("pa")
    var pa: String? = "",
    @SerializedName("pl")
    var pl: String? = "",
    @SerializedName("ps")
    var ps: String? = "",
    @SerializedName("pt")
    var pt: String? = "",
    @SerializedName("qu")
    var qu: String? = "",
    @SerializedName("rm")
    var rm: String? = "",
    @SerializedName("ro")
    var ro: String? = "",
    @SerializedName("ru")
    var ru: String? = "",
    @SerializedName("sa")
    var sa: String? = "",
    @SerializedName("sc")
    var sc: String? = "",
    @SerializedName("sd")
    var sd: String? = "",
    @SerializedName("se")
    var se: String? = "",
    @SerializedName("sh")
    var sh: String? = "",
    @SerializedName("si")
    var si: String? = "",
    @SerializedName("sk")
    var sk: String? = "",
    @SerializedName("sl")
    var sl: String? = "",
    @SerializedName("sm")
    var sm: String? = "",
    @SerializedName("sn")
    var sn: String? = "",
    @SerializedName("so")
    var so: String? = "",
    @SerializedName("sq")
    var sq: String? = "",
    @SerializedName("sr")
    var sr: String? = "",
    @SerializedName("st")
    var st: String? = "",
    @SerializedName("su")
    var su: String? = "",
    @SerializedName("sv")
    var sv: String? = "",
    @SerializedName("sw")
    var sw: String? = "",
    @SerializedName("ta")
    var ta: String? = "",
    @SerializedName("te")
    var te: String? = "",
    @SerializedName("tg")
    var tg: String? = "",
    @SerializedName("th")
    var th: String? = "",
    @SerializedName("tk")
    var tk: String? = "",
    @SerializedName("tl")
    var tl: String? = "",
    @SerializedName("to")
    var to: String? = "",
    @SerializedName("tr")
    var tr: String? = "",
    @SerializedName("tt")
    var tt: String? = "",
    @SerializedName("tw")
    var tw: String? = "",
    @SerializedName("ug")
    var ug: String? = "",
    @SerializedName("uk")
    var uk: String? = "",
    @SerializedName("ur")
    var ur: String? = "",
    @SerializedName("uz")
    var uz: String? = "",
    @SerializedName("vi")
    var vi: String? = "",
    @SerializedName("vo")
    var vo: String? = "",
    @SerializedName("wa")
    var wa: String? = "",
    @SerializedName("wo")
    var wo: String? = "",
    @SerializedName("yi")
    var yi: String? = "",
    @SerializedName("yo")
    var yo: String? = "",
    @SerializedName("zh")
    var zh: String? = "",
    @SerializedName("zu")
    var zu: String? = ""
) : Parcelable