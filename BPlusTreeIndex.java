class BPlusTreeIndex {

    static class Record {
        int userId;
        String timestamp;

        Record(int userId, String timestamp) {
            this.userId = userId;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return "(UserID: " + userId + ", Timestamp: " + timestamp + ")";
        }
    }

    Record[] records = {
            new Record(42, "2024-01-05"),
            new Record(42, "2024-01-10"),
            new Record(42, "2024-01-25"),
            new Record(50, "2024-01-15"),
            new Record(60, "2024-02-01")
    };

    void findByUserAndRange(int userId, String start, String end) {
        System.out.println("\nDocuments for User ID " + userId +
                " between " + start + " and " + end + ":");

        for (Record r : records) {
            if (r.userId == userId &&
                    r.timestamp.compareTo(start) >= 0 &&
                    r.timestamp.compareTo(end) <= 0) {
                System.out.println(r);
            }
        }
    }

    void findByUser(int userId) {
        System.out.println("\nDocuments for User ID " + userId + ":");

        for (Record r : records) {
            if (r.userId == userId) {
                System.out.println(r);
            }
        }
    }

    void findByTimestamp(String timestamp) {
        System.out.println("\nDocuments with Timestamp " + timestamp + ":");

        for (Record r : records) {
            if (r.timestamp.equals(timestamp)) {
                System.out.println(r);
            }
        }
    }

    public static void main(String[] args) {

        BPlusTreeIndex index = new BPlusTreeIndex();

        System.out.println("MongoDB Compound Index B+ Tree Simulation");
        System.out.println("Index Order: (user_id, timestamp)");

        index.findByUserAndRange(
                42,
                "2024-01-01",
                "2024-01-31"
        );

        index.findByUser(42);

        index.findByTimestamp("2024-01-15");
    }
}