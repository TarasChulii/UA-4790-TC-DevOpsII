# Google Cloud Storage Static Website 

Creating a static website using Google Cloud Storage involves hosting a collection of HTML, <br>
CSS, JavaScript, and other static files on Google Cloud Storage and configuring it to serve these files publicly. <br>
Below is a step-by-step guide to complete this task:

#### Prerequisites:

1. **Google Cloud Platform Account:**
   - Ensure that you have a Google Cloud Platform (GCP) account. If not, sign up for a GCP account.

2. **Project Setup:**
   - Create a new project or use an existing one in the Google Cloud Console.

#### Steps:

### 1. Enable Google Cloud Storage API:

- Go to the [Google Cloud Console](https://console.cloud.google.com/).
- Open the navigation menu and select "APIs & Services" > "Dashboard."
- Click on the "+ ENABLE APIS AND SERVICES" button.
- Search for "Cloud Storage API" and enable it for your project.

### 2. Create a Cloud Storage Bucket:

- In the Google Cloud Console, navigate to "Storage" > "Browser."
- Click on the "+ CREATE BUCKET" button.
- Enter a globally unique bucket name, choose your storage class, and configure any additional settings.
- Click "Create" to create the bucket.

### 3. Upload Static Website Files:

- Upload your static website files (HTML, CSS, JavaScript, images, etc.) to the Cloud Storage bucket.
- Ensure that your main HTML file is named "index.html" or set the default object in the bucket settings.

### 4. Configure Bucket for Static Website Hosting:

- In the Cloud Storage Console, navigate to your bucket.
- Click on the "Permissions" tab.
- Click "Add members" to add "allUsers" with the role "Storage Object Viewer." This makes your objects publicly readable.
- Go to the "Website" tab and configure the Main page and 404 page (if necessary).

### 5. Make the Website Publicly Accessible:

- Update the permissions of your objects to be publicly accessible. You can do this when uploading or after uploading by setting the permissions to "Public" or "AllUsers."

### 6. Access Your Static Website:

- Once configured, your static website is accessible via the URL: `https://storage.googleapis.com/YOUR_BUCKET_NAME/index.html` or using the bucket's default domain.
- Go to the [following URL to see default web page](https://storage.googleapis.com/static-web-site-example/index.html)
- Go to the [following URL to see default 404 page](https://storage.googleapis.com/static-web-site-example/error.html)

### 7. (Optional) Configure a Custom Domain:

- If you have a custom domain, you can configure it to point to your Cloud Storage bucket. Follow the instructions in the Cloud Storage documentation for setting up a custom domain.

Your static website is now hosted on Google Cloud Storage and is publicly accessible. Any changes or updates to your website files can be easily managed by updating the files in the Cloud Storage bucket.
