# Define the MongoDB Atlas Provider
terraform {
  required_providers {
    mongodbatlas = {
      source = "mongodb/mongodbatlas"
    }
  }
  required_version = ">= 0.13"
}

provider "mongodbatlas" {
  public_key  = var.MONGODB_ATLAS_PUBLIC_KEY
  private_key = var.MONGODB_ATLAS_PRIVATE_KEY
}


# Create a Project
resource "mongodbatlas_project" "myproject" {
  name   = var.MONGODB_ATLAS_PROJECT_NAME
  org_id = var.MONGODB_ATLAS_ORGANIZATION_ID
  tags = {
    DeployedBy = "Terraform"
  }
}


# Create an Atlas Cluster
resource "mongodbatlas_advanced_cluster" "mycluster" {
  project_id = mongodbatlas_project.myproject.id
  name = var.MONGODB_ATLAS_CLUSTER_NAME
  cluster_type = "REPLICASET"
  replication_specs = [
    {
      region_configs = [
        {
          electable_specs = {
            instance_size = "M0"
          }
          provider_name = "TENANT"
          backing_provider_name = "AWS"
          priority      = 7
          region_name   = "US_EAST_1"
        }
      ]
    }
  ]

  tags = {
    DeployedBy = "Terraform"
  }

  depends_on = [
    mongodbatlas_project.myproject
  ]
}


# Create a Database User
resource "mongodbatlas_database_user" "bob" {
  username           = var.MONGODB_ATLAS_DB_USER_NAME
  password           = var.MONGODB_ATLAS_DB_USER_PASS
  project_id         = mongodbatlas_project.myproject.id
  auth_database_name = "admin"

  roles {
    role_name     = "readWrite"
    database_name = "db"
  }
  depends_on = [
    mongodbatlas_advanced_cluster.mycluster
  ]
}


# Open up your IP Access List to all, but this comes with significant potential risk.
locals {
  cidr_block_list = [
  "0.0.0.0/1",
  "128.0.0.0/1"
  ]
}

resource "mongodbatlas_project_ip_access_list" "cidr" {
  for_each   = toset(local.cidr_block_list)
  project_id = mongodbatlas_project.myproject.id
  cidr_block = each.value

  depends_on = [ mongodbatlas_project.myproject ]
}

# Output the connection string for convenience
output "mongodb_connection_string" {
  value = mongodbatlas_advanced_cluster.mycluster.connection_strings.standard_srv
}
