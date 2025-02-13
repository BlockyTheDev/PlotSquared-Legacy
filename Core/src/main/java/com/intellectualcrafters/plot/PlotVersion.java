package com.intellectualcrafters.plot;

public class PlotVersion {
    public final int year, month, day, hash, build;

    public PlotVersion(int year, int month, int day, int hash, int build) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hash = hash;
        this.build = build;
    }

    /*public PlotVersion(String version, String commit, String date) {
        String[] split = version.substring(version.indexOf('=') + 1).split("\\.");
        this.build = Integer.parseInt(split[1]);
        this.hash = Integer.parseInt(commit.substring(commit.indexOf('=') + 1), 16);
        String[] split1 = date.substring(date.indexOf('=') + 1).split("\\.");
        this.year = Integer.parseInt(split1[0]);
        this.month = Integer.parseInt(split1[1]);
        this.day = Integer.parseInt(split1[2]);
    }*/

    public PlotVersion(String version) {
        String[] split = version.substring(version.indexOf('=') + 1).split("-");
        if (split[0].equals("unknown")) {
            this.year = month = day = hash = build = 0;
            return;
        }
        String[] date = split[0].split("\\.");
        this.year = Integer.parseInt(date[0]);
        this.month = Integer.parseInt(date[1]);
        this.day = Integer.parseInt(date[2]);
        if (split[1].equals("SNAPSHOT")) {
            this.hash = 0;
            this.build = 0;
        } else {
            this.hash = Integer.parseInt(split[1], 16);
            this.build = Integer.parseInt(split[2]);
        }
    }

    /*public static PlotVersion tryParse(String version, String commit, String date) {
        try {
            return new PlotVersion(version, commit, date);
        } catch (Exception e) {
            e.printStackTrace();
            return new PlotVersion(0, 0, 0, 0, 0);
        }
    }*/

    public static PlotVersion tryParse(String version) {
        try {
            return new PlotVersion(version);
        } catch (Exception ignore) {
            ignore.printStackTrace();
            return new PlotVersion(0, 0, 0, 0, 0);
        }
    }

    public String versionString() {
        if (hash == 0 && build == 0) {
            return "NoVer-SNAPSHOT";
        } else {
            return "3." + build;
        }
    }
    /*@Override
    public String toString() {
        if (hash == 0 && build == 0) {
            return "PlotSquared-NoVer-SNAPSHOT";
        } else {
            return "PlotSquared-3." + build;
        }
    }*/
    @Override
    public String toString() {
        if (hash == 0 && build == 0) {
            return "PlotSquared-" + year + "." + month + "." + day + "-SNAPSHOT";
        } else {
            return "PlotSquared-" + year + "." + month + "." + day + "-" + Integer.toHexString(hash) + "-" + build;
        }
    }
}