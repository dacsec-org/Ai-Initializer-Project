import { DownloadActions } from '../enums/download-actions';
import { from, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import client from './connection-factory';

const SERVICE = "downloaders-service";

/**
 * <h1>{@link LoadUnloadBridge}</h1>
 * @param action
 * @constructor
 */
export const LoadUnloadBridge = (action: DownloadActions): Observable<any> => {
  return from(
    client.call(SERVICE, "download", { action })
  ).pipe(map(response => response));
};
