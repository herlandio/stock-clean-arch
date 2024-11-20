## Sistema de Gerenciamento de Estoque

### Descrição

Este projeto é um sistema de gerenciamento de estoque projetado para escalabilidade, resiliência e eficiência. Ele integra tecnologias modernas como Kafka, Redis, PostgreSQL, Swagger, Prometheus, Micrometer, e segue os princípios da Arquitetura Limpa (Clean Architecture) assim como Solid e Clean Code. O sistema oferece funcionalidades avançadas, como alertas inteligentes, auditoria completa e monitoramento em tempo real.

### Funcionalidades Principais

**1. Alertas Inteligentes**

- **Níveis de Criticidade Diferenciados:**

    Permite configurar múltiplos níveis de criticidade, como "crítico", "moderado" e "baixo", com ações específicas para cada nível.

-  **Prioridade de Notificação:**

    Utiliza tópicos diferentes no Kafka para tratar notificações críticas com maior prioridade, garantindo baixa latência.

**2. Gerenciamento de Estoque**
    
- Controle de entrada e saída de produtos.

- Notificações automáticas para reabastecimento quando o estoque atinge níveis mínimos.

**3. Escalabilidade e Resiliência**

- **Retry e Dead Letter Queues:**

    Configura tópicos de retrabalho e filas mortas no Kafka para mensagens que falham no envio ou processamento.

- **Balanceamento de Carga:**

    Implementa múltiplos consumidores Kafka para processar mensagens de forma escalável.

**4. Auditoria e Logs**

- **Histórico de Notificações:**
    
    Armazena todas as notificações enviadas em um log para auditoria e conformidade.

- **Métricas e Alertas Internos:**

    Integração com Prometheus e Grafana para monitorar o desempenho e gerar alertas em tempo real.

### Tecnologias Utilizadas

 - **Kafka:** Gerenciamento de mensagens para notificações e comunicação assíncrona.
 - **Redis:** Cache de alta performance para reduzir a latência e melhorar a escalabilidade.
 - **PostgreSQL:** Banco de dados relacional para armazenar dados do estoque e logs de auditoria.
 - **Swagger:** Documentação interativa para APIs RESTful.
 - **Prometheus & Grafana:** Monitoramento e visualização de métricas.
 - **Micrometer:** Coleta de métricas para monitoramento de aplicações Java.
 - **Arquitetura Limpa:** Organização modular e desacoplada para facilitar manutenção e evolução do sistema.

### Documentação da API
    
A API é documentada com Swagger, permitindo explorar endpoints e realizar testes interativos.

Para acessar a documentação:

1. Inicie o servidor do sistema.
2. Acesse: `http://localhost:8080/swagger-ui.html`

### Instruções para Execução

### Pré-requisitos
- Docker e Docker Compose instalados.

### Passos

**1. Clone o repositório:**

```
git clone https://github.com/herlandio/stock-clean-arch
``` 

**2. Suba os serviços com Docker Compose:**

```
docker-compose up -d 
```

### Monitoramento

- **Dashboard Prometheus:**
    
    Métricas detalhadas disponíveis em: `http://localhost:9090`

- **Visualização Grafana:**
    
    Dashboards configuráveis disponíveis em: `http://localhost:3000`