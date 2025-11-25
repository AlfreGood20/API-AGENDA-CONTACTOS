package com.api.api_contacto.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Constantes con los códigos HTTP como `String` (útiles para anotaciones)
 * y un mapa con las frases descriptivas (reason phrases).
 */
public final class StatusCode {

	private StatusCode() {}

	// 1xx Informational
	public static final String CONTINUE = "100";
	public static final String SWITCHING_PROTOCOLS = "101";
	public static final String PROCESSING = "102";
	public static final String EARLY_HINTS = "103";

	// 2xx Success
	public static final String OK = "200";
	public static final String CREATED = "201";
	public static final String ACCEPTED = "202";
	public static final String NON_AUTHORITATIVE_INFORMATION = "203";
	public static final String NO_CONTENT = "204";
	public static final String RESET_CONTENT = "205";
	public static final String PARTIAL_CONTENT = "206";
	public static final String MULTI_STATUS = "207";
	public static final String ALREADY_REPORTED = "208";
	public static final String IM_USED = "226";

	// 3xx Redirection
	public static final String MULTIPLE_CHOICES = "300";
	public static final String MOVED_PERMANENTLY = "301";
	public static final String FOUND = "302";
	public static final String SEE_OTHER = "303";
	public static final String NOT_MODIFIED = "304";
	public static final String USE_PROXY = "305";
	public static final String TEMPORARY_REDIRECT = "307";
	public static final String PERMANENT_REDIRECT = "308";

	// 4xx Client Error
	public static final String BAD_REQUEST = "400";
	public static final String UNAUTHORIZED = "401";
	public static final String PAYMENT_REQUIRED = "402";
	public static final String FORBIDDEN = "403";
	public static final String NOT_FOUND = "404";
	public static final String METHOD_NOT_ALLOWED = "405";
	public static final String NOT_ACCEPTABLE = "406";
	public static final String PROXY_AUTHENTICATION_REQUIRED = "407";
	public static final String REQUEST_TIMEOUT = "408";
	public static final String CONFLICT = "409";
	public static final String GONE = "410";
	public static final String LENGTH_REQUIRED = "411";
	public static final String PRECONDITION_FAILED = "412";
	public static final String PAYLOAD_TOO_LARGE = "413";
	public static final String URI_TOO_LONG = "414";
	public static final String UNSUPPORTED_MEDIA_TYPE = "415";
	public static final String RANGE_NOT_SATISFIABLE = "416";
	public static final String EXPECTATION_FAILED = "417";
	public static final String IM_A_TEAPOT = "418";
	public static final String MISDIRECTED_REQUEST = "421";
	public static final String UNPROCESSABLE_ENTITY = "422";
	public static final String LOCKED = "423";
	public static final String FAILED_DEPENDENCY = "424";
	public static final String TOO_EARLY = "425";
	public static final String UPGRADE_REQUIRED = "426";
	public static final String PRECONDITION_REQUIRED = "428";
	public static final String TOO_MANY_REQUESTS = "429";
	public static final String REQUEST_HEADER_FIELDS_TOO_LARGE = "431";
	public static final String UNAVAILABLE_FOR_LEGAL_REASONS = "451";

	// 5xx Server Error
	public static final String INTERNAL_SERVER_ERROR = "500";
	public static final String NOT_IMPLEMENTED = "501";
	public static final String BAD_GATEWAY = "502";
	public static final String SERVICE_UNAVAILABLE = "503";
	public static final String GATEWAY_TIMEOUT = "504";
	public static final String HTTP_VERSION_NOT_SUPPORTED = "505";
	public static final String VARIANT_ALSO_NEGOTIATES = "506";
	public static final String INSUFFICIENT_STORAGE = "507";
	public static final String LOOP_DETECTED = "508";
	public static final String NOT_EXTENDED = "510";
	public static final String NETWORK_AUTHENTICATION_REQUIRED = "511";

	private static final Map<String, String> REASON_PHRASES;

	static {
		Map<String, String> m = new HashMap<>();
		m.put(CONTINUE, "Continue");
		m.put(SWITCHING_PROTOCOLS, "Switching Protocols");
		m.put(PROCESSING, "Processing");
		m.put(EARLY_HINTS, "Early Hints");

		m.put(OK, "OK");
		m.put(CREATED, "Created");
		m.put(ACCEPTED, "Accepted");
		m.put(NON_AUTHORITATIVE_INFORMATION, "Non-Authoritative Information");
		m.put(NO_CONTENT, "No Content");
		m.put(RESET_CONTENT, "Reset Content");
		m.put(PARTIAL_CONTENT, "Partial Content");

		m.put(MOVED_PERMANENTLY, "Moved Permanently");
		m.put(FOUND, "Found");
		m.put(SEE_OTHER, "See Other");
		m.put(NOT_MODIFIED, "Not Modified");

		m.put(BAD_REQUEST, "Bad Request");
		m.put(UNAUTHORIZED, "Unauthorized");
		m.put(FORBIDDEN, "Forbidden");
		m.put(NOT_FOUND, "Not Found");
		m.put(METHOD_NOT_ALLOWED, "Method Not Allowed");
		m.put(NOT_ACCEPTABLE, "Not Acceptable");
		m.put(REQUEST_TIMEOUT, "Request Timeout");
		m.put(CONFLICT, "Conflict");
		m.put(GONE, "Gone");
		m.put(PAYLOAD_TOO_LARGE, "Payload Too Large");
		m.put(UNPROCESSABLE_ENTITY, "Unprocessable Entity");
		m.put(TOO_MANY_REQUESTS, "Too Many Requests");

		m.put(INTERNAL_SERVER_ERROR, "Internal Server Error");
		m.put(NOT_IMPLEMENTED, "Not Implemented");
		m.put(BAD_GATEWAY, "Bad Gateway");
		m.put(SERVICE_UNAVAILABLE, "Service Unavailable");
		m.put(GATEWAY_TIMEOUT, "Gateway Timeout");

		REASON_PHRASES = Collections.unmodifiableMap(m);
	}

	/**
	 * Obtener la frase descriptiva asociada a un código HTTP (por ejemplo "OK").
	 * @param codeString código en formato String (p. ej. "200")
	 * @return frase descriptiva o cadena vacía si no se conoce
	 */
	public static String reason(String codeString) {
		return REASON_PHRASES.getOrDefault(codeString, "");
	}

	/**
	 * Obtener la frase descriptiva a partir de un código entero.
	 */
	public static String reason(int code) {
		return reason(String.valueOf(code));
	}
}