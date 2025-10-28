# 🏠 Real Estate Property Search Engine

A **real estate property search engine** where users can search, filter, and explore properties to **buy or rent** based on multiple criteria such as:

- Location (city, lat/lon, geospatial search)
- Price range
- Number of rooms
- Property type (apartment, house, land, commercial)
- Availability status

The project integrates with **Elasticsearch** for fast faceted and geospatial search, and a **PostgreSQL (with PostGIS)** database for structured property data.  
Frontend is built with **React + Leaflet/Google Maps** to display results in both list and map views.

---

## 📌 Features
- 🔍 Advanced property search (price, rooms, city, type)
- 🌍 Map integration (Leaflet/Google Maps) for location-based browsing
- ⚡ Fast faceted search with **Elasticsearch**
- 🗄️ Relational database (PostgreSQL + PostGIS) for structured data
- 📤 Property management (CRUD APIs for properties & users)
- 📱 Responsive UI with React/Next.js

---

## 🆚 Why **Elasticsearch** Instead of Solr?

Both Solr and Elasticsearch are powerful search engines, but Elasticsearch was chosen due to:

### ✅ Pros of Elasticsearch
- **Easier setup & integration** → JSON-based REST API is very developer-friendly.
- **Better ecosystem** → Widely adopted with strong community support and modern tooling (Kibana).
- **Real-time indexing** → Faster updates when new properties are added.
- **Geospatial queries** → Native support for location-based searches (within radius, bounding boxes).
- **Scalability** → Horizontal scaling is smoother for distributed search.

### ⚠️ Cons of Elasticsearch
- **Memory-heavy** → Requires careful tuning for large datasets.
- **Licensing** → Newer versions have restrictive licenses (need to check OSS distribution).
- **Less focus on faceted navigation out of the box** compared to Solr (though achievable with aggregations).

### ✅ Pros of Solr (for comparison)
- Strong faceted search support (common in e-commerce & real estate).
- Easier to tune for **classic text search & filtering**.
- Pure open-source under Apache.

### ⚠️ Cons of Solr
- Configuration-heavy (XML-based).
- Smaller ecosystem compared to Elasticsearch.
- Slightly weaker geospatial capabilities than Elasticsearch.

**Conclusion:** Since our project requires **real-time updates** and **geospatial property search**, Elasticsearch was selected over Solr.


# 🏡 Property Suggestion Feature

This feature provides **real-time property title suggestions** as users type in the search bar.  
It is built using **Spring Boot**, **PostgreSQL**, and **Elasticsearch** for fast and efficient search performance.

---

## 🚀 Feature Overview

- **Endpoint:** `GET /suggest?prefix=<text>`
- Returns a list of property titles that start with the given prefix.
- Uses **Elasticsearch** to fetch suggestions quickly.
- Automatically indexes new properties when they are saved.

---

## 🧩 Architecture Overview

1. **Property** — Stored in PostgreSQL.
2. **PropertyDocument** — Indexed in Elasticsearch.
3. **PropertyServiceImpl** — Handles saving, searching, and suggestions.
4. **PropertyController** — Exposes `/suggest` endpoint to the frontend.

---

## 🔍 Example Usage

**Request**
```http
GET /suggest?prefix=A

### 🌍 Geo-Search (Location-Based Filtering)

This feature allows users to **search properties within a specific radius** from a given location using Elasticsearch’s `geo_point` and `geo_distance` queries.  
It helps users find nearby properties — for example, *“find all properties within 10 km of Colombo.”*

---

#### 🧩 Feature Overview
- Each property includes a **`location`** field of type `geo_point` (latitude, longitude).  
- The API uses Elasticsearch’s **geo-distance query** to return all matching properties inside a radius.  
- Built using **Spring Boot** + **Spring Data Elasticsearch**.

---

#### 🧠 Example Entity
```java
@Document(indexName = "property")
public class PropertyDocument {
    @Id
    private Long id;
    private String title;
    private String description;
    private Double price;

    @GeoPointField
    private GeoPoint location; // latitude & longitude
}
```

---

#### 🚀 API Endpoint
**Endpoint**
```
GET /properties/nearby?lat=6.9271&lon=79.8612&distance=10km
```

**Parameters**
| Name | Type | Example | Description |
|------|------|----------|-------------|
| `lat` | double | `6.9271` | Latitude of center point |
| `lon` | double | `79.8612` | Longitude of center point |
| `distance` | string | `10km` | Search radius (supports `m`, `km`, `mi`) |

---

#### 🧾 Example Request
```
GET /properties/nearby?lat=6.9271&lon=79.8612&distance=5km
```

#### ✅ Example JSON Response
```json
[
  {
    "id": 1,
    "title": "Luxury Apartment in Colombo",
    "description": "3-bedroom sea view apartment",
    "price": 25000000.0,
    "location": {
      "lat": 6.9271,
      "lon": 79.8612
    }
  },
  {
    "id": 2,
    "title": "Modern Villa near Galle Face",
    "description": "Spacious family home within city limits",
    "price": 42000000.0,
    "location": {
      "lat": 6.9210,
      "lon": 79.8550
    }
  }
]
```

---

#### ⚙️ How It Works
The backend performs a **geo-distance query** using Spring Data Elasticsearch:
```java
NativeQuery query = NativeQuery.builder()
    .withQuery(q -> q
        .geoDistance(g -> g
            .field("location")
            .distance(distance)
            .location(new GeoPoint(lat, lon))
        )
    )
    .build();
```

---

#### 💡 Why It’s Valuable
- Enables **map-based and nearby searches**
- Demonstrates understanding of **geospatial queries in Elasticsearch**
- Adds **real-world, production-ready functionality** to the Property Search Engine


