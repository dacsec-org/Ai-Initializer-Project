import { from, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import client from './ConnectionFactory';
import { ModelActions } from '../enums/ModelActions';

const SERVICE = 'models-service'; // Name from @Bridge annotation

/**
 * <h1>{@link ModelsBridge}</h1>
 * @param action
 * @constructor
 */
export const ModelsBridge = (action: ModelActions): Observable<any> => {
  return from(
    client.call(SERVICE, 'processModels', { action })
  ).pipe(map(response => response));
}
