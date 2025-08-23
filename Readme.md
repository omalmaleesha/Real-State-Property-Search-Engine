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

---


