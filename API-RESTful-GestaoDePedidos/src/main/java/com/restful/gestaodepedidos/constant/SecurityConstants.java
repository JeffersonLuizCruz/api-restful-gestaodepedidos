package com.restful.gestaodepedidos.constant;

/*Classe de criação de token. Aqui o token é apenas montado.
 * A classe JwtManager do pacote comrestful.gestaodepedidos.security
 * é a responsável pela criação do token propiadamente dito.
 * */
public class SecurityConstants {
	
	public static final int JWT_EXP_DAYS = 5; // Esse token vai expirar depois de 5 dias
	public static final String API_KEY = "199107";//Chave da API
	public static final String JWT_PROVIDER = "Dearer";//Portador do token ou provedor do token(O token ficar gravado no head da solicitação)
	/*Depois de estar autenticado terá as permissões; Os roles ficará no corpo ou no pyload do jwt. Essa variável
	 * é a chave deste role.
	 * */
	public static final String JWT_ROLE_KEY = "role";
}
