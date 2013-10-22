package com.wordpress.nprogramming;

@SuppressWarnings("UnusedDeclaration")
public enum BitOperation {
    NOT {
        @Override
        public String getSymbol() {
            return "~";
        }

        @Override
        public Long calculate(Long a, Long b) {
            return ~a;
        }
    },
    AND {
        @Override
        public String getSymbol() {
            return "&";
        }

        @Override
        public Long calculate(Long a, Long b) {
            return a & b;
        }
    },
    OR {
        @Override
        public String getSymbol() {
            return "|";
        }

        @Override
        public Long calculate(Long a, Long b) {
            return a | b;
        }
    },
    XOR {
        @Override
        public String getSymbol() {
            return "^";
        }

        @Override
        public Long calculate(Long a, Long b) {
            return a ^ b;
        }
    },
    LSHIFT {
        @Override
        public String getSymbol() {
            return "<<";
        }

        @Override
        public Long calculate(Long a, Long b) {
            return a << b;
        }
    },
    RSHIFT {
        @Override
        public String getSymbol() {
            return ">>";
        }

        @Override
        public Long calculate(Long a, Long b) {
            return a >> b;
        }
    };

    public abstract String getSymbol();

    public abstract Long calculate(Long a, Long b);
}
