package com.ckcest.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.log4j.Logger;
//import org.apache.lucene.queryparser.surround.query.SimpleTerm.MatchingTermVisitor;

public class CatalogItemParser {
	/*
	 * private static final Logger logger =
	 * Logger.getLogger(CatalogItemParser.class); public static void init(){
	 * NLPIRTool.init(); } public static void exit(){ NLPIRTool.exit(); }
	 */
	public static void main(String[] args) {
		String str = "��6��1 ����";
		System.out.println(trim(str));
	}
	
	private static String specialstr = "^.[ά|��|Ԫ|��|��|��|��]";
	
	public static String complex2simple(String title) {
		if (title == null || title.equals(""))
			return "";
		String result = Complex2SimpleTool.conver(title, 0);
		return result;
	}

	public static String trim(String title) {
		String str = title;
		Pattern pattern = Pattern.compile("^(\\d+){0,5}[��|\\s]+");
		Matcher matcher = pattern.matcher(str);
		str = matcher.replaceAll("");
		
		pattern = Pattern.compile(specialstr);
		matcher = pattern.matcher(str);
		if (!matcher.find()) {
			pattern = Pattern.compile("^[һ�����������߰˾�ʮ��ǧ]+[��|\\s]+");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			
			// str = str.replaceAll("[��|\\s]+|\t|\r|\n", "");//ɾ�����пո�

			String[] specialChar = { "��", "��", "��", "��", "��", "��", "��", "��", "��",
					"��", "��", "��", "��", "��", "��", "��", "\\[", "\\]", "��", "��", "��",
					"��", "��", "��", "��", "��", "��", "��", "��", "��  ", "��", "��", "��" };

			for (int i = 0; i < specialChar.length; i++) {
				str = str.replaceAll(specialChar[i], "");
			}/*
			 * for (int i = 0; i < startChar.length; i++) { if
			 * (str.startsWith(startChar[i])) str = str.replace(startChar[i], ""); }
			 */

			pattern = Pattern
					.compile("^(?i)Chapter [1-9]|��([1-9][0-9]*|[һ�����������߰˾�ʮ��ǧ]+)[��|��|ƪ|����|��Ԫ|�½�|��ƪ|��ƪ|��|��|��|��|��|��|Ļ|��|��|��]");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			// .{1,2}[��| |\\.|��|,|��|:]*
			// logger.debug("first pattern: " + str);

			pattern = Pattern
					.compile("^(\\d+[��|\\s]*[\\.����\\-��������]){0,5}[��|\\s]*\\d*");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			// logger.debug("second pattern: " + str);

			pattern = Pattern
					.compile("^[\\(|��|��]*.{1,3}[\\)|��|��]*[��|\\s|\\.|��|��|��|,|��|:|��]+");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			// logger.debug("third pattern: " + str);

			pattern = Pattern.compile("^[\\(|��|��|\\[].{1,2}[\\)|��|��|\\]]");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			// logger.debug("fourth pattern: " + str);

			pattern = Pattern
					.compile("^[\\(|��|��|\\[]*[��|\\s]*[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789��������������������������������������������������������][��|\\s]*[\\)|��|��|\\]]");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");

			pattern = Pattern
					.compile("^([ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz][-��](\\d){1,3})");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
		}

		str = str.trim();
		pattern = Pattern.compile(specialstr);
		matcher = pattern.matcher(str);
		if (!matcher.find()) {
			pattern = Pattern.compile("^[һ�����������߰˾�ʮ��ǧ]+[��|\\s]*[\\.|��|��|��]+");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			// logger.debug("sixth pattern: " + str);

			pattern = Pattern
					.compile("^[\\(|��]*[��|\\s]*[һ�����������߰˾�ʮ��ǧ]+[��|\\s]*[\\)|��]*[��|\\s|\\.|��|��|��|,|��|:|��]+");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			// logger.debug("seven pattern: " + str);

			pattern = Pattern
					.compile("^[\\(|��]*[��|\\s]*[һ�����������߰˾�ʮ��ǧ]+[��|\\s]*[\\)|��]*");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");

			pattern = Pattern
					.compile("^[��|����|��|ͼ][��|\\s]*([1-9][0-9]*|[һ�����������߰˾�ʮ]+)[��|\\s|\\.|��|��|��|,|��|:|��]*");
			matcher = pattern.matcher(str);
			if (matcher.find())
				str = "";

			// ��xxx ��ɣ�xxx���ͣ�xxx���࣬ xxxѡ��,
			// ����xxx,���õ�xxx������xxx��xxx����,xxx�����ͣ�xxx���࣬xxx��Ʒ�֣�xxx��ҪƷ�ֽ��ܣ�xxx���
			// pattern =
			// Pattern.compile("[���|����|����|ѡ��|����|������|����|��Ʒ��|��ҪƷ�ֽ���|���|Ʒ��]+$");
			// pattern = Pattern.compile("^[(����)|���õ�|����]+");
			String[] p2 = { "����", "�ο�����", "����", "����", "����", "����", "����", "ϰ��",
					"˼����", "��ϰ��", "����С��", "С��", "С��", "��ϰ", "�����ܽ�", "����", "�ܽ�",
					"������", "��", "��¼", "����", "����", "����", "����", "����", "����", "����",
					"��˵", "�ſ�", "ʵ��", "����", "����", "ʵ��", "�Ծ�", "ʵϰ", "�ο���", "����",
					"����", "��ҵ", "����", "��", "����"};
			for (String p : p2) {
				if (str.startsWith(p)) {
					str = "";
					break;
				}
			}

			String[] p1 = { "��", "��", "��", "��", "��", "��", "��ƪ", "��ƪ", "��", "��", "��" };
			for (String p : p1) {
				if (str.startsWith(p)) {
					str = str.replace(p, "");
					break;
				}
			}
			/*
			 * String[] p1 =
			 * {"����","��","�ο�����","����","����","����","��","��","��","����","��","ϰ��"
			 * ,"˼����","��ϰ��","����С��","С��","С��","��ϰ","�����ܽ�","�ܽ�","������"};
			 */
			pattern = Pattern
					.compile("^[\\(|��|��]*[��|\\s]*(([һ�����������߰˾�ʮ��ǧ]+)|(\\d+))[��|\\s]*[\\)|��|��]*[��|\\s|\\.|��|��|��|,|��|:|��]*");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");

			// logger.debug("seven pattern:" + str);
			/*
			 * for(String p:p2){ if(str.endsWith(p)){ str = str.replace(p, "");
			 * break; } }
			 */
			// logger.debug("eight pattern:" + str);

			pattern = Pattern
					.compile("^��[0-9һ�����������߰˾�ʮ��ǧ]+[��|��|ƪ|����|��Ԫ|�½�|��ƪ|��ƪ|��|��|��|��|��|��|Ļ|��|��|��]+");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			// logger.debug("nine pattern: " + str);

			if (str.length() == 1) {
				pattern = Pattern
						.compile("^[ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789�����Ƥ������������ˤ��������������äƤȤʤ��̤ͤΤϤҤդؤۤޤߤ���������������������������������������������������������������������������������������������ĥȥʥΥϥեإͥޥ�������� ]");
				matcher = pattern.matcher(str);
				str = matcher.replaceAll("");
			}

			pattern = Pattern
					.compile("^([\\.|��|��|��|,|��|:|��|\\|/|*|@|#|$|%|&|!|��||~|?|��|��|\\(|\\)|��|��|\\[|\\]])");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");

			pattern = Pattern
					.compile("([\\.|��|��|��|,|��|:|��|\\|/|*|@|#|$|%|&|!|��||~|?|��|��])$");
			matcher = pattern.matcher(str);
			str = matcher.replaceAll("");
			
			pattern = Pattern
					.compile("^([��|��|��|Ļ|��|��|��])");
			matcher = pattern.matcher(str.trim());
			if (matcher.find())
				str = "";
			
			if (str.contains("����") || str.contains("�Ծ�") || str.contains("����") || str.contains("��"))
				str = "";

			int l = 0;
			int r = 0;
			if (str.contains("��") || str.contains("(")) {
				System.out.println("debug:" + str);
				if (str.contains("��")) l = str.indexOf("��");
				else l = str.indexOf("(");
				
				if (str.contains("��") || str.contains(")")) {
					if (str.contains("��")) r = str.indexOf("��");
					else r = str.indexOf(")");
					
					if (r < str.length()) {
						str = str.substring(0, l) + str.substring(r + 1, str.length());
					}else str = "";
					
				}
			}
		}
		
		return str.trim();
	}

	/**
	 * �޳��ġ��͡�������
	 * 
	 * @param str
	 * @return
	 */
	public static String remove_de_he_ji_yu(String str) {
		String[] s = str.split(" ");
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("��") || s[i].equals("��") || s[i].equals("��")
					|| s[i].equals("��") || s[i].equals("�е�") || s[i].equals("����") || s[i].equals("֮"))
				continue;
			else
				result.append(s[i]).append(" ");
		}
		return result.toString();
	}

	/**
	 * �޳�����������
	 * 
	 * @param str
	 * @return
	 */
	public static String remove_Punctuation(String str) {
		String[] s = str.split(" ");
		StringBuilder result = new StringBuilder();
		Pattern pattern = Pattern.compile("^[��,��:����.����\"\"����''?��()��������-]");
		Matcher matcher;
		for (int i = 0; i < s.length; i++) {
			matcher = pattern.matcher(s[i]);
			if (matcher.find())
				continue;
			else
				result.append(s[i]).append(" ");
		}
		return result.toString();
	}
	
	
	public static boolean isQuestion(String title) {
		if (title.contains("���") || title.contains("����")
				|| title.contains("ʲô ") || title.endsWith("?")
				|| title.endsWith("��") || title.contains("��Щ"))
			return true;
		return false;
	}

}
