import java.awt.Color;
import java.sql.SQLException;

import com.fredrikpedersen.module3.chapter3.Main_CDI;
import com.fredrikpedersen.module3.chapter3.Main_JEE;
import com.fredrikpedersen.module4.chapter2.Main_TCF;
import com.fredrikpedersen.module4.chapter2.Main_TwR;
import com.fredrikpedersen.module4.chapter2.Main_TwR2;
import com.fredrikpedersen.module4.chapter3.Main;
import com.fredrikpedersen.module6.chapter1.Main_BLOB_Read;
import com.fredrikpedersen.module6.chapter1.Main_BLOB_Store;
import com.fredrikpedersen.module6.chapter1.Main_CLOB_Read;
import com.fredrikpedersen.module6.chapter1.Main_CLOB_Store;
import com.fredrikpedersen.module6.chapter2.Main_INOUT;
import com.fredrikpedersen.module7.chapter2.Main_AutoCommit;
import com.fredrikpedersen.module7.chapter2.Main_ManualCommit;
import util.PrintUtil;

public class Demo {

	public static void main (String[] args) throws Exception {
		if (args.length == 0) {
			Demo.inValidMessage();
			return;
		}

		try {
			switch (args[0]) {
			case "m2c1":
				com.fredrikpedersen.module2.chapter1.Main.main(null);
				break;
			case "m3c1":
				com.fredrikpedersen.module3.chapter1.Main.main(null);
				break;
			case "m3c2":
				com.fredrikpedersen.module3.chapter2.Main.main(null);
				break;
			case "m3c3":
				String mainToRun = args[1];
				if (mainToRun.equalsIgnoreCase("JEE")) {
					Main_JEE.main(null);
				} else if (mainToRun.equalsIgnoreCase("CDI")) {
					Main_CDI.main(null);
				} else {
					System.out.println("You must secify either JEE or CDI as an imput paramters");
					System.out.println("For example: java -cp target/corejdbc.jar Demo m3c3 JEE");
				}
				break;
			case "m4c1":
				com.fredrikpedersen.module4.chapter1.Main.main(null);
				break;
			case "m4c2":
				if (args.length == 2) {
					if (args[1].equalsIgnoreCase("TCF")) {
						Main_TCF.main(null);
						break;
					} else if (args[1].equalsIgnoreCase("TwR")) {
						Main_TwR.main(null);
						break;
					} else if (args[1].equalsIgnoreCase("TwR2")) {
						Main_TwR2.main(null);
						break;
					}
				}
				System.out.println("You must secify which main you wish to run: TCF, TwR, or TwR2");
				System.out.println("For example: java -cp target/corejdbc.jar Demo m4c2 TwR");

				break;
			case "m4c3":
				Main.main(null);
				break;
			case "m4c4":
				com.fredrikpedersen.module4.chapter4.Main.main(null);
				break;
			case "m4c5":
				com.fredrikpedersen.module4.chapter5.Main.main(null);
				break;
			case "m4c6":
				com.fredrikpedersen.module4.chapter6.Main.main(null);
				break;
			case "m5c1":
				com.fredrikpedersen.module5.chapter1.Main.main(null);
				break;
			case "m5c2":
				com.fredrikpedersen.module5.chapter2.Main.main(null);
				break;
			case "m5c3":
				com.fredrikpedersen.module5.chapter3.Main.main(null);
				break;
			case "m5c4":
				com.fredrikpedersen.module5.chapter4.Main.main(null);
				break;
			case "m6c1":
				if (args.length == 3) {
					if(args[1].equalsIgnoreCase("CLOB") && args[2].equalsIgnoreCase("STORE")) {
						Main_CLOB_Store.main(null);
					}else if(args[1].equalsIgnoreCase("CLOB") && args[2].equalsIgnoreCase("READ")) {
						Main_CLOB_Read.main(null);
					}else if(args[1].equalsIgnoreCase("BLOB") && args[2].equalsIgnoreCase("STORE")) {
						Main_BLOB_Store.main(null);
					}else if(args[1].equalsIgnoreCase("BLOB") && args[2].equalsIgnoreCase("READ")) {
						Main_BLOB_Read.main(null);
					}
				} else {
					System.out.println("You must secify which main you wish to run:");
					System.out.println("Syntax: java -cp target/corejdbc.jar Demo m6c2 [CLOB,BLOB] [STORE,READ]");
				}
				break;
			case "m6c2":
				if (args.length <= 1) {
					com.fredrikpedersen.module6.chapter2.Main.main(null);
				} else if (args[1].equalsIgnoreCase("INOUT")) {
					Main_INOUT.main(null);

				} else {
					System.out.println("You must secify which main you wish to run:");
					System.out.println("Either: java -cp target/corejdbc.jar Demo m6c2");
					System.out.println("    Or: java -cp target/corejdbc.jar Demo m6c2 INOUT");
				}
				break;
			case "m7c1":
				com.fredrikpedersen.module7.chapter1.Main.main(null);
				break;
			case "m7c2":
				if (args.length == 2) {
					if (args[1].equalsIgnoreCase("AC")) {
						Main_AutoCommit.main(null);
						break;
					} else if (args[1].equalsIgnoreCase("MC")) {
						Main_ManualCommit.main(null);
						break;
					}
				}
				System.out.println("You must secify which main you wish to run: AC or MC");
				System.out.println("For example: java -cp target/corejdbc.jar Demo m4c2 MC");

				break;
			case "m7c3":
				com.fredrikpedersen.module7.chapter3.Main.main(null);
				break;
			default:
				inValidMessage();

			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			if (ex instanceof SQLException || ex.getCause() instanceof SQLException) {
				printSQLException((SQLException) ex);
			} else {
				PrintUtil.colorPrint("An exception was thrown:", Color.orange, true);
				PrintUtil.colorPrint(ex.getMessage(), Color.orange, true);
			}
		}
	}

	public static void printSQLException(SQLException sqlEx) {
		PrintUtil.colorPrint("A SQLException was thrown:", Color.orange, true);
		PrintUtil.colorPrint(sqlEx.getMessage(), Color.orange, true);
		PrintUtil.colorPrint("SQLState: " + sqlEx.getSQLState(), Color.orange, true);
		PrintUtil.colorPrint("VendorError: " + sqlEx.getErrorCode(), Color.orange, true);

	}

	public static void inValidMessage() {
		System.out.println("You must specifiy which a valid demo to run. For example:");
		PrintUtil.colorPrint("java -cp target/corejdbc.jar Main ", Color.blue, false);
		PrintUtil.colorPrint("m2c1", Color.orange, true);
	}

}
