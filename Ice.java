import java.util.*;

public class Ice {
	public static void main(String args[]) {
		int flag = Integer.valueOf(args[0]);
		double[] ndays = new double[] { 118, 151, 121, 96, 110, 117, 132, 104, 125, 118, 125, 123, 110, 127, 131, 99, 126, 144, 136, 126,
		        91, 130, 62, 112, 99, 161, 78, 124, 119, 124, 128, 131, 113, 88, 75, 111, 97, 112, 101, 101, 91, 110, 100, 130, 111, 107,
		        105, 89, 126, 108, 97, 94, 83, 106, 98, 101, 108, 99, 88, 115, 102, 116, 115, 82, 110, 81, 96, 125, 104, 105, 124, 103, 106,
		        96, 107, 98, 65, 115, 91, 94, 101, 121, 105, 97, 105, 96, 82, 116, 114, 92, 98, 101, 104, 96, 109, 122, 114, 81, 85, 92,
		        114, 111, 95, 126, 105, 108, 117, 112, 113, 120, 65, 98, 91, 108, 113, 110, 105, 97, 105, 107, 88, 115, 123, 118, 99, 93,
		        96, 54, 111, 85, 107, 89, 87, 97, 93, 88, 99, 108, 94, 74, 119, 102, 47, 82, 53, 115, 21, 89, 80, 101, 95, 66, 106, 97, 87,
		        109, 57, 87, 117, 91, 62, 65 };
		double[] years = new double[ndays.length];
		int t = 0;
		double pb0 = 0;
		double pb1 = 0;
		double df = 0;

		for (int i = 1855; i <= 2016; i++) {
			years[t] = i;
			t++;
		}

		if (flag == 100) {
			for (int m = 0; m < ndays.length; m++) {
				System.out.println((int) years[m] + " " + (int) ndays[m]);
			}
		}
		if (flag == 200) {
			System.out.println(ndays.length);
			double mean = 0;
			for (int m = 0; m < ndays.length; m++) {
				mean += ndays[m];
			}
			mean /= ndays.length;
			System.out.println(String.format("%.2f", mean));

			for (int k = 0; k < ndays.length; k++) {
				df += (ndays[k] - mean) * (ndays[k] - mean);
			}
			df /= (ndays.length - 1);
			System.out.println(String.format("%.2f", Math.sqrt(df)));
		}
		if (flag == 300) {
			double b0 = Double.parseDouble(args[1]);
			double b1 = Double.parseDouble(args[2]);
			double err = 0;
			for (int p = 0; p < ndays.length; p++) {
				err += (b0 + b1 * years[p] - ndays[p]) * (b0 + b1 * years[p] - ndays[p]);
			}
			err /= ndays.length;
			System.out.println(String.format("%.2f", err));
		}

		if (flag == 400) {
			double b0 = Double.parseDouble(args[1]);
			double b1 = Double.parseDouble(args[2]);
			for (int w = 0; w < ndays.length; w++) {
				pb0 += (b0 + b1 * years[w] - ndays[w]);
				pb1 += (b0 + b1 * years[w] - ndays[w]) * years[w];
			}
			pb0 /= ndays.length / 2;
			pb1 /= ndays.length / 2;
			System.out.println(String.format("%.2f", pb0));
			System.out.println(String.format("%.2f", pb1));
		}
		if (flag == 500) {
			double n = Double.parseDouble(args[1]);
			double T = Double.parseDouble(args[2]);
			double b0 = 0;
			double b1 = 0;
			int count = 1;
			double meanErr = 0;
			while (T > 0) {
				for (int w = 0; w < ndays.length; w++) {
					pb0 += (b0 + b1 * years[w] - ndays[w]);
					pb1 += (b0 + b1 * years[w] - ndays[w]) * years[w];
				}
				pb0 /= ndays.length / 2;
				pb1 /= ndays.length / 2;
				b0 -= n * pb0;
				b1 -= n * pb1;
				pb0 = 0;
				pb1 = 0;
				T--;
				meanErr = 0;
				for (int p = 0; p < ndays.length; p++) {
					meanErr += (b0 + b1 * years[p] - ndays[p]) * (b0 + b1 * years[p] - ndays[p]);
				}
				meanErr /= (double) ndays.length;
				System.out.println(
				        count + " " + String.format("%.2f", b0) + " " + String.format("%.2f", b1) + " " + String.format("%.2f", meanErr));
				count++;
			}
		}
		if (flag == 700) {
			double xbar = 0;
			double ybar = 0;
			double b0Hat = 0;
			double b1Hat = 0;
			double denominator = 0;
			double futureYear = Double.parseDouble(args[1]);

			for (int y = 0; y < ndays.length; y++) {
				xbar += years[y];
				ybar += ndays[y];
			}
			xbar /= years.length;
			ybar /= ndays.length;

			for (int g = 0; g < ndays.length; g++) {
				b1Hat += (years[g] - xbar) * (ndays[g] - ybar);

			}

			for (int g = 0; g < ndays.length; g++) {
				denominator += ((years[g] - xbar) * (years[g] - xbar));
			}
			b1Hat /= denominator;
			b0Hat = ybar - b1Hat * xbar;

			System.out.println(String.format("%.2f", b0Hat + futureYear * b1Hat));
		}

		if (flag == 800) {
			double xbar = 0;
			double n = Double.parseDouble(args[1]);
			double T = Double.parseDouble(args[2]);
			double[] nYears = new double[ndays.length];
			double b0 = 0;
			double b1 = 0;
			int count = 1;
			double meanErr = 0;

			for (int y = 0; y < ndays.length; y++) {
				xbar += years[y];
			}
			xbar /= years.length;

			for (int k = 0; k < ndays.length; k++) {
				df += (years[k] - xbar) * (years[k] - xbar);
			}
			df /= (double) (ndays.length - 1);

			for (int b = 0; b < ndays.length; b++) {
				nYears[b] = (years[b] - xbar) / Math.sqrt(df);
			}

			while (T > 0) {
				for (int w = 0; w < ndays.length; w++) {
					pb0 += (b0 + b1 * nYears[w] - ndays[w]);
					pb1 += (b0 + b1 * nYears[w] - ndays[w]) * nYears[w];
				}
				pb0 /= ndays.length / 2;
				pb1 /= ndays.length / 2;
				b0 -= n * pb0;
				T--;

				b1 -= n * pb1;
				pb0 = 0;
				pb1 = 0;

				meanErr = 0;
				for (int p = 0; p < ndays.length; p++) {
					meanErr += (b0 + b1 * nYears[p] - ndays[p]) * (b0 + b1 * nYears[p] - ndays[p]);
				}
				meanErr /= (double) ndays.length;
				System.out.println(
				        count + " " + String.format("%.2f", b0) + " " + String.format("%.2f", b1) + " " + String.format("%.2f", meanErr));
				count++;
			}
		}
		if (flag == 900) {
			double xbar = 0;
			double n = Double.parseDouble(args[1]);
			double T = Double.parseDouble(args[2]);
			double[] nYears = new double[ndays.length];
			double b0 = 0;
			double b1 = 0;
			double r = 0;
			int count = 1;
			double meanErr = 0;
			Random rng = new Random();

			for (int y = 0; y < ndays.length; y++) {
				xbar += years[y];
			}
			xbar /= years.length;

			for (int k = 0; k < ndays.length; k++) {
				df += (years[k] - xbar) * (years[k] - xbar);
			}
			df /= (double) (ndays.length - 1);

			for (int b = 0; b < ndays.length; b++) {
				nYears[b] = (years[b] - xbar) / Math.sqrt(df);
			}

			while (T > 0) {
				r = rng.nextDouble();
				r = r * ndays.length;
				r = Math.floor(r);
				int intR = (int) r;
				pb0 = 2 * (b0 + b1 * nYears[intR] - ndays[intR]);
				pb1 = 2 * (b0 + b1 * nYears[intR] - ndays[intR]) * (nYears[intR]);//
				b0 -= n * pb0;
				b1 -= n * pb1;
				T--;

				meanErr = 0;
				pb0 = 0;
				pb1 = 0;

				for (int p = 0; p < ndays.length; p++) {
					meanErr += (b0 + b1 * nYears[p] - ndays[p]) * (b0 + b1 * nYears[p] - ndays[p]);
				}

				meanErr /= (double) ndays.length;
				System.out.println(
				        count + " " + String.format("%.2f", b0) + " " + String.format("%.2f", b1) + " " + String.format("%.2f", meanErr));
				count++;
			}
		}

	}
}