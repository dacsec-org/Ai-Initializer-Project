import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./ConnectionFactory";
import { DownloadAction } from '../enums/DownloadAction';

const SERVICE = "downloaders-service";

/**
 * <h1>{@link SearchModelsBridge}</h1>
 */
export const SearchModelsBridge
  = (action: DownloadAction): Observable<any> => {
  return from(
    client.call(
      SERVICE,
      "download",
      { action })
  ).pipe(map(response => response));
};
