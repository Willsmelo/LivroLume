package br.com.fiap.api.LivroLume.enums;

public enum Plano {
    BRONZE(1) {
        @Override
        public Plano proximoPlano() {
            return SILVER;
        }

        @Override
        public Plano planoAtras() {
            return BRONZE;
        }
    },SILVER(2) {
        @Override
        public Plano proximoPlano() {
            return GOLD;
        }

        @Override
        public Plano planoAtras() {
            return BRONZE;
        }
    },GOLD(3) {
        @Override
        public Plano proximoPlano() {
            return PLATINUM;
        }

        @Override
        public Plano planoAtras() {
            return SILVER;
        }
    },PLATINUM(4) {
        @Override
        public Plano proximoPlano() {
            return DIAMOND;
        }

        @Override
        public Plano planoAtras() {
            return GOLD;
        }
    },DIAMOND(5) {
        @Override
        public Plano proximoPlano() {
            return DIAMOND;
        }

        @Override
        public Plano planoAtras() {
            return PLATINUM;
        }
    };

    private int valor;

    Plano(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public abstract Plano proximoPlano();
    public abstract Plano planoAtras();
}
