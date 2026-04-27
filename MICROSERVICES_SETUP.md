# Microservices Docker Setup - Step by Step

## Step 1: Build Maven Projects

### Eureka
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\eureka
./mvnw clean package -DskipTests
```

### Products
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\products
./mvnw clean package -DskipTests
```

### Orders
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\orders
./mvnw clean package -DskipTests
```

### Zuul
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\zuul
./mvnw clean package -DskipTests
```

---

## Step 2: Build Docker Images

### Eureka Image
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\eureka
docker build -t eureka:latest .
```

### Products Image
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\products
docker build -t products:latest .
```

### Orders Image
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\orders
docker build -t orders:latest .
```

### Zuul Image
```bash
cd C:\Users\nicol\Documents\Especialización\Desarrollo de aplicaciones web\clases\clase2\zuul
docker build -t zuul:latest .
```

---

## Step 3: Run All 4 Services

### Terminal 1: Eureka Server (Port 8761)
```bash
docker run -p 8761:8761 eureka:latest
```
Wait 30-40 seconds for Eureka to fully start.

### Terminal 2: Products Service (Port 8086)
```bash
docker run -p 8086:8086 --env eureka_url=http://172.17.0.2:8761/eureka products:latest
```

### Terminal 3: Orders Service (Port 8082)
```bash
docker run -p 8082:8082 --env eureka_url=http://172.17.0.2:8761/eureka orders:latest
```

### Terminal 4: Zuul Gateway (Port 8762)
```bash
docker run -p 8762:8762 --env eureka_url=http://172.17.0.2:8761/eureka zuul:latest
```

---

## Verify Services Running

```bash
docker ps --format "table {{.Names}}\t{{.Image}}\t{{.Ports}}\t{{.Status}}"
```

Should show 4 containers all with "Up" status.

---

## Access Services

- **Eureka Dashboard:** http://localhost:8761
- **Products API:** http://localhost:8086/products
- **Orders API:** http://localhost:8082/orders
- **Zuul Gateway:** http://localhost:8762/ms-products/products
- **Zuul Gateway:** http://localhost:8762/ms-orders/orders

