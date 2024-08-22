Makersharks Search API - Proof of Concept
Overview

Makersharks is developing a search functionality where buyers can find manufacturers based on customized requirements. This project is a Proof of Concept (POC) for the Search API that will allow buyers to search for suppliers by location, nature of business, and manufacturing capabilities.

Supplier Details
The suppliers are represented by the following details:

supplier_id: Unique identifier for each supplier.

company_name: Name of the supplier company.

website: The website URL of the supplier.

location: City where the supplier is located (assuming one supplier maps to one location).

nature_of_business: Type of business, possible values:

small_scale

medium_scale

large_scale

manufacturing_processes: Manufacturing processes offered by the supplier

API Endpoints

1. /api/supplier/query [POST]
   
Purpose:

Retrieve a list of manufacturer(s) based on specified criteria such as location, nature of business, and manufacturing processes.

Request Body:

location: (String) The city where the supplier is located.

nature_of_business: (String) The scale of business (small_scale, medium_scale, large_scale).

manufacturing_process: (String) The required manufacturing process.

limit: (Integer) The maximum number of results to return.

Example Request:

json

Copy code
{
  "location": "India",
  "nature_of_business": "small_scale",
  "manufacturing_process": "3d_printing",
  "limit": 10
}
Expected Response:

A JSON array of manufacturers matching the given criteria, each with the following details:

supplier_id

company_name

website

location

nature_of_business

manufacturing_processes

Example Response:


json

Copy code

[

 {
 
      "supplierId": 13,
      
      "companyName": "Windy City Machines",
      
      "website": "www.windycitymachines.com",
      
      "natureOfBusiness": "Tool Manufacturing",
      
      "manufacturingProcesses": "CNC Machining, Lathe Work, Milling",
      
      "location": "Chicago"
    }
  
  ...
]

Copy code

[

 {
 
    {
      "supplierId": 1,
      "companyName": "Global Supplies Inc.",
      "website": "www.globalsupplies.com",
      "natureOfBusiness": "Automotive Parts",
      "manufacturingProcesses": "Injection Molding, Extrusion, Thermoforming",
      "location": "New York"
    },
    {
      "supplierId": 2,
      "companyName": "City Tech Solutions",
      "website": "www.citytechsolutions.com",
      "natureOfBusiness": "Electronics",
      "manufacturingProcesses": "Surface Mount Technology, Wave Soldering, Automated Optical Inspection",
      "location": "New York"
    },
    {
      "supplierId": 3,
      "companyName": "Empire Plastics",
      "website": "www.empireplastics.com",
      "natureOfBusiness": "Plastic Products",
      "manufacturingProcesses": "Blow Molding, Injection Molding, Rotational Molding",
      "location": "New York"
    },
    {
      "supplierId": 4,
      "companyName": "NYC Textiles",
      "website": "www.nyctextiles.com",
      "natureOfBusiness": "Textile Manufacturing",
      "manufacturingProcesses": "Weaving, Dyeing, Finishing",
      "location": "New York"
    },
    {
      "supplierId": 5,
      "companyName": "Manhattan Foods",
      "website": "www.manhattanfoods.com",
      "natureOfBusiness": "Food Production",
      "manufacturingProcesses": "Flash Freezing, Vacuum Packaging, Pasteurization",
      "location": "New York"
    },
    {
      "supplierId": 6,
      "companyName": "Big Apple Metals",
      "website": "www.bigapplemetals.com",
      "natureOfBusiness": "Metal Fabrication",
      "manufacturingProcesses": "Forging, Casting, Machining",
      "location": "New York"
    },
    {
      "supplierId": 7,
      "companyName": "Statue Glassworks",
      "website": "www.statueglassworks.com",
      "natureOfBusiness": "Glassware Production",
      "manufacturingProcesses": "Glass Blowing, Sandblasting, Annealing",
      "location": "New York"
    },
    {
      "supplierId": 8,
      "companyName": "Brooklyn Electronics",
      "website": "www.brooklynelectronics.com",
      "natureOfBusiness": "Consumer Electronics",
      "manufacturingProcesses": "3D Printing, Injection Molding, PCB Assembly",
      "location": "New York"
    },
    {
      "supplierId": 9,
      "companyName": "NY Ceramics",
      "website": "www.nyceramics.com",
      "natureOfBusiness": "Ceramic Production",
      "manufacturingProcesses": "Kiln Firing, Glazing, Slip Casting",
      "location": "New York"
    },
    {
      "supplierId": 10,
      "companyName": "Hudson Valley Chemicals",
      "website": "www.hudsonvalleychemicals.com",
      "natureOfBusiness": "Chemical Manufacturing",
      "manufacturingProcesses": "Batch Processing, Distillation, Extraction",
      "location": "New York"
    }

    }
Guidelines

1. Development

Framework: Use Spring Boot to build the RESTful API.

Pagination: Implement pagination to handle cases where the number of results exceeds a certain limit.

Validation: Ensure proper input validation and sanitization.

Exception Handling: Implement proper exception handling mechanisms to manage errors gracefully.

3. Security

Best Practices: Follow security best practices to protect sensitive data and prevent vulnerabilities.

TODO: For more complex security implementations, outline the approach as a TODO in the codebase.
4. Testing

Unit Tests: Write unit tests to verify the correct behaviour of the API endpoint.

5. Documentation
Swagger: Use Swagger to document the API for easy understanding and testing.

Setup and Installation

Prerequisites

Java 17

Maven 
Spring Boot

Steps

Clone the repository.

Navigate to the project directory.

Build the project using Maven

Run the application:

By running src code

Access the API documentation at [http://localhost:8080/swagger-ui.html.](http://localhost:8080/swagger-ui/index.html#/supplier-controller/querySuppliers].
