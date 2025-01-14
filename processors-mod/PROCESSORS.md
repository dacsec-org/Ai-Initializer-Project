# Processors

### Pre-processors:
1. **Data Cleaning**: Pre-processors help clean and prepare raw data, removing noise and inconsistencies to ensure the data is in a suitable format for the AI model.
2. **Feature Extraction**: They extract relevant features from the raw data, which can improve the model's performance and accuracy.
3. **Normalization**: Pre-processors normalize data, ensuring that all features contribute equally to the model's learning process.
4. **Data Augmentation**: They can augment data, increasing the diversity of the training set and helping the model generalize better.

### Post-processors:
1. **Result Interpretation**: Post-processors help interpret the model's output, converting it into a human-readable format.
2. **Error Analysis**: They analyze errors and provide insights into the model's performance, helping identify areas for improvement.
3. **Actionable Insights**: Post-processors can transform the model's output into actionable insights, facilitating decision-making processes.
4. **Integration**: They integrate the model's output with other systems or workflows, ensuring seamless operation within the larger application.

## AudioProcessor
The `AudioProcessor` class processes audio data by converting a byte array to an `AudioInputStream` and back to a byte array. It also provides methods to get the file input/output location and input/output device information.

## CsvProcessor
The `CsvProcessor` class processes CSV data by trimming lines and replacing whitespace with commas. It implements the `StringProcessingAdviserIface` interface.

## DocumentProcessor
The `DocumentProcessor` class processes document data by reading and writing files as strings. It implements the `StringProcessingAdviserIface` interface.

## EncodingProcessor
The `EncodingProcessor` class encodes text into unique integer values and provides a method to decode the integer back to the original text. It implements the `StringProcessingAdviserIface` interface.

## HtmlProcessor
The `HtmlProcessor` class processes HTML content by cleaning it using the Jsoup library. It implements the `StringProcessingAdviserIface` interface.

## ImageProcessor
The `ImageProcessor` class processes image data by converting images to grayscale, resizing them, and converting them to Base64. It implements the `StringProcessingAdviserIface` interface.

## JsonProcessor
The `JsonProcessor` class processes JSON data by pretty-printing it using the Jackson library. It implements the `StringProcessingAdviserIface` interface.

## MissingValuesProcessor
The `MissingValuesProcessor` class processes strings by replacing null or empty values with "N/A". It implements the `StringProcessingAdviserIface` interface.

## PdfProcessor
The `PdfProcessor` class processes PDF data. It provides methods to get the file input/output location and input/output device information. It implements the `StringProcessingAdviserIface` interface.

## TextProcessor
The `TextProcessor` class processes text data by normalizing, removing punctuation, stop words, numbers, whitespace, HTML tags, special characters, expanding contractions, and removing URLs and email addresses. It implements the `StringProcessingAdviserIface` interface.

## TokenizationProcessor
The `TokenizationProcessor` class processes text data by tokenizing it into a list of words. It implements the `StringProcessingAdviserIface` interface.

## VectorizationProcessor
The `VectorizationProcessor` class processes JSON data by extracting and concatenating values from the JSON structure.

## VideoProcessor
The `VideoProcessor` class processes video data by reading and writing byte arrays. It also provides methods to get the file input/output location and input/output device information. It implements the `ByteProcessingAdviserIface` interface.

## XmlProcessor
The `XmlProcessor` class processes XML data by pretty-printing it using the DOM and Transformer libraries. It implements the `StringProcessingAdviserIface` interface.
